const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const ProductsSchema = new Schema({
    productName: { type: String, required: true },
    productImage: { type: String, required: true },
    productPrice: { type: Number, required: true },
    description: { type: String, required: true },
    quantity: { type: Number, required: true },
    evaluate: {type: Number, required: true},
    categoryID: { type: String, required: true },
    colors: [{
        colorName: { type: String, required: true },
        colorCode: { type: String, required: true }, // Mã màu sắc, ví dụ: #ff0000
        // Các trường thông tin màu sắc khác nếu cần
    }]
}, {
    timestamps: true
});

module.exports = mongoose.model('products', ProductsSchema);
