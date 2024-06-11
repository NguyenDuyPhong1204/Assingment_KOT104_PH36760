var express = require('express');
var router = express.Router();
const User = require('../models/user');
const JWT = require('jsonwebtoken');
const crypto = require('crypto'); // Import crypto để tạo token
const Token = require('../models/token');
const bcrypt = require('bcrypt')
const productType = require('../models/productType')
const product = require('../models/products')
const Cart = require('../models/cart')
const Favorite = require('../models/favorite')
const verifiedEmail = require('../config/common/mail');
const SECRETKEY = "NDP";

router.post('/register', async (req, res) => {
  try {
    let user = await User.findOne({ email: req.body.email });
    if (user) {
      return res.status(400).send("User with given email is existing!");
    }
    const hashedPassword = await bcrypt.hash(req.body.password, 10);
    user = new User({
      email: req.body.email,
      password: hashedPassword,
      fullname: req.body.fullname
    });

    const result = await user.save();

    const token = new Token({
      userId: user._id,
      token: crypto.randomBytes(16).toString("hex")
    });

    await token.save();
    console.log(token);

    const link = `http://localhost:3000/api/confirm/${token.token}`;
    await verifiedEmail(user.email, link);
    console.log(user.email);

    res.status(200).send({
      message: "Đăng ký thành công. Vui lòng kiểm tra email để xác nhận tài khoản!",
      user: result
    });
  } catch (error) {
    console.error("Registration failed:", error);
    res.status(500).send("Internal Server Error");
  }
});


//active account
//active account
router.get('/confirm/:token', async (req, res) => {
  try {
    const token = await Token.findOne({
      token: req.params.token
    });
    if (!token) {
      return res.status(400).send("Invalid token");
    }

    await User.updateOne({ _id: token.userId }, { $set: { verified: true } });
    await Token.findByIdAndDelete(token._id);
    res.send("Email verified");
  } catch (error) {
    console.error("An error occurred:", error);
    res.status(500).send("Internal Server Error");
  }
});


//login
router.post('/login', async (req, res) => {
  try {
    const { email, password, id} = req.body;
    const findUser = await User.findOne({ email: email });

    if (findUser) {
      // Xác minh tài khoản đã xác minh (bỏ qua phần này nếu không cần thiết)
      if (findUser.verified) {
        const match = await bcrypt.compare(password, findUser.password);
        if (match) {
          // Mật khẩu khớp
          res.status(200).send({
            email: email,
            password: password,
             // Gửi thông tin nhạy cảm có thể tiềm ẩn nguy cơ bảo mật
          });
        } else {
          // Mật khẩu không khớp
          res.status(400).send("Mật khẩu không đúng");
        }
      } else {
        // Tài khoản chưa xác minh
        res.status(400).send("Bạn cần xác minh tài khoản của mình");
      }
    } else {
      // Người dùng không tồn tại
      res.status(400).send("Người dùng không tồn tại");
    }
  } catch (err) {
    console.error(err);
    res.status(500).send("Lỗi máy chủ");
  }
});


//product type
router.get('/get-productType', async (req, res) => {
  try {
    const result = await productType.find()
    if (result) {
      res.json({
        "status": 200,
        "message": "Lấy dữ liệu thành công",
        "data": result
      })
    } else {
      res.json({
        "status": 400,
        "message": "Lấy dữ liệu thất bại",
        "data": []
      })
    }
  } catch (error) {
    console.log(error);
  }
})

//danh sách product
router.get('/get-product/:categoryID', async (req, res) => {
  try {
    const { categoryID } = req.params
    const result = await product.find({ categoryID: categoryID })
    if (result) {
      res.json({
        "status": 200,
        "message": "Lấy dữ liệu thành công",
        "data": result
      })
    } else {
      res.json({
        "status": 400,
        "message": "Lấy dữ liệu thất bại",
        "data": []
      })
    }
  } catch (error) {
    console.log(error);
  }
})

//lấy thông tin product theo id product
router.get('/get-product-by-id/:productID', async (req, res) => {
  const { productID } = req.params
  const result = await product.findById(productID)
  if (result) {
    res.json({
      "status": 200,
      "message": "Lấy dữ liệu thành công",
      "data": result
    })
  }else{
    res.json({
      "status": 400,
      "message": "Lấy dữ liệu thất bại",
      "data": []
  })
  }
})

//danh sách giỏ hàng 
router.get("/get-list-cart", async(req, res) =>{
  try {

    const result = await Cart.find()
    if(result){
      res.json({
        "status": 200,
        "message": "Lấy dữ liệu thành công",
        "data": result
    })
    }
  } catch (error) {
    res.json({
      "status": 400,
      "message": "Lấy dữ liệu thất bại",
      "data": []
  })
  }
})

router.get('/get-list-favorite', async function (req, res) {
  try {
      const { userId } = req.params;
      const result = await Favorite.find({ userId: userId })
      if (result) {
          res.json({
              "status": 200,
              "message": "Lấy dữ liệu thành công",
              "data": result
          })
      } else {
          res.json({
              "status": 400,
              "message": "Lấy dữ liệu thất bại",
              "data": []
          })
      }
  } catch (error) {
      console.log(error);
  }
});

//thêm vào giỏ hàng
router.post("/add-cart", async(req, res) =>{
  try {
    const data = req.body
    const newCart = new Cart({
      productId: data.productId,
      productName: data.productName,
      productImage: data.productImage,
      quantity: data.quantity,
      productPrice: data.productPrice
    })
    const result = await newCart.save()
    if(result){
      res.json({
        "status": 200,
        "message": "Thêm thành công",
        "data": result
    });
    }else{
      res.json({
        "status": 400,
        "message": "Thêm mới thất bại",
        "data": []
    });
    }
  } catch (error) {
    console.log(error);
  }
})

//thêm vào yêu thích
router.post('/add-favorite', async function (req, res) {
  try {
      const data = req.body;
      const newFavorite = new Favorite({
        productId: data.productId,
        productName: data.productName,
        productImage: data.productImage,
        quantity: data.quantity,
        productPrice: data.productPrice
      })

      const result = await newFavorite.save();
      if (result) {
          res.json({
              "status": 200,
              "message": "Thêm thành công",
              "data": result
          });
      } else {
          res.json({
              "status": 400,
              "message": "Thêm mới thất bại",
              "data": []
          });
      }
  } catch (error) {
      console.log(error);
  }
});

//xoá khỏi giỏ hàng
router.delete('/delete-cart-by-id/:id', async function (req, res) {
  try {
      const { id } = req.params;
      const result = await Cart.findByIdAndDelete(id);//tìm và thực hiện xoá
      if (result) {
          res.json({
              "status": 200,
              "message": "Xóa thành công",
              "data": result
          })
      } else {
          res.json({
              "status": 400,
              "message": "Xóa thất bại",
              "data": []
          })
      }
  } catch (error) {
      console.log(error);
  }
});


//xoá khỏi yêu thích

router.delete('/delete-favorite-by-id/:id', async function (req, res) {
  try {
      const { id } = req.params;
      const result = await Favorite.findByIdAndDelete(id);//tìm và thực hiện xoá
      if (result) {
          res.json({
              "status": 200,
              "message": "Xóa thành công",
              "data": result
          })
      } else {
          res.json({
              "status": 400,
              "message": "Xóa thất bại",
              "data": []
          })
      }
  } catch (error) {
      console.log(error);
  }
});

router.delete('/delete-cart', async (req, res) => {
  try {
      const result = await Cart.deleteMany();
      if (result) {
          res.json({
              "status": 200,
              "message": "Xóa giỏ hàng thành công",
              "data": result
          });
      } else {
          res.json({
              "status": 400,
              "message": "Không thể xóa giỏ hàng",
              "data": []
          });
      }
  } catch (error) {
      console.log(error);
      res.json({
          "status": 500,
          "message": "Lỗi server",
          "data": []
      });
  }
});

module.exports = router;
