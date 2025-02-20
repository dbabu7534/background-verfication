import React from "react";
import axios from "axios";

const  BASE_URL = 'http://localhost:8080'


const getHeader = () => {
    const token = localStorage.getItem("token")
    if(!token){
        console.error("No Token Found in Local Storage")
    }
    return {
        Authorization : `Bearer ${token}`,
        "Content-type" : "application/json"
    };
}


const getUsers = async () => {
    try {
        const response = await axios.get(`${BASE_URL}/users/user-detail/7`, {headers: getHeader()} );
        console.log(response.data);
    } catch (error) {
        console.error("Failed to fetch users: " + error.message);
    }
};

const Test = () => {
    return (
        <a href="#" onClick={(e) => { e.preventDefault(); getUsers(); }}>
            Get Users
        </a>
    );
};

export default Test;
