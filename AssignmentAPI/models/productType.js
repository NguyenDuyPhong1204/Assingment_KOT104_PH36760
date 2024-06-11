const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const ProductType = new Schema({
    title: {type: String},
    imageType: {type: String}
},{
    timestamps: true
});

module.exports = mongoose.model('productType', ProductType);