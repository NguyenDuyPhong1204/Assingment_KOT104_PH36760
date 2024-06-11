const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const Users = new Schema({
    email: { type: String, unique: true },
    password: { type: String },
    fullname: { type: String },
    verified: {type: Boolean, default: false}
}, {
    timestamps: true
});

module.exports = mongoose.model('user', Users)
