const mongoose = require('mongoose');
mongoose.set('strictQuery', true);

const local = "mongodb://localhost:27017/API_KOT104";

const connect = async () =>{
    try {
        await mongoose.connect(local);
        console.log('Connect successfully!');
    } catch (error) {
        console.log(error);
        console.log('Connect failure!');
    }
}

module.exports = {connect};