const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const Carts = new Schema({
    productId: {type: Schema.Types.ObjectId, ref: "products"},
    productName: {type: String},
    productImage: {type: String},
    quantity: {type: Number},
    productPrice: {type: Number}
},{
    timestamps: true
});

module.exports = mongoose.model('cart', Carts)