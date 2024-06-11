var nodemailer = require("nodemailer");
const verifiedEmail = async (email, link) => {
    try {
        let transporter = nodemailer.createTransport({
            service: 'Gmail',
            auth: {
                user: "nguyenduyphong12122004@gmail.com",
                pass: "yukvqushrqfmgbzn"
            }
        });
        //send email
        let info = await transporter.sendMail({
            from: "nguyenduyphong12122004@gmail.com",//sender email
            to: email,
            subject: "Xác nhận tài khoản",
            html: `<h3>Chào mừng bạn đến với chúng tôi</h3>
            <p>Click vào đây để xác nhận tài khoản</p>
            <a href="${link}">Xác nhận</a>`
        })

        console.log("email send successfuly");
    } catch (error) {
        console.log("email send failed", error);
    }
}

module.exports = verifiedEmail;