import axios, {} from 'axios'

export default class ApiService {

    static BASE_URL = 'http://localhost:8080'

    static getHeader(){
        const token = localStorage.getItem("token")
        if(!token){
            console.error("No Token Found in Local Storage")
        }
        return {
            Authorization : `Bearer ${token}`,
            "Content-type" : "application/json"
        };
    }

    static getHeaderForFileUpload(){
        const token = localStorage.getItem("token")
        if(!token){
            console.log("no token Found");
        }
        return {
            Authorization : `Bearer ${token}`,
            "Content-type" : "multipart/form-data"
        }
    }

    /**AUTH */

    /* This  register a new user */
    static async registerUser(registration){
        const response = await axios.post(`${this.BASE_URL}/auth/register`, registration)
        return response.data
    }
    /* This  login a registered user */
    static async loginUser(loginDetails) {
        const response = await axios.post(`${this.BASE_URL}/auth/login`, loginDetails)
        return response.data
    }

    static async saveEducational(id, educational){
        const response = await axios.post(`${this.BASE_URL}/users/educational/${id}`, educational, {headers: this.getHeader()})
        return response.data
    }

    

    static async savePersonal(id, personal){
        const response = await axios.post(`${this.BASE_URL}/users/personal/${id}`, personal, {headers: this.getHeader()} )        
        return response.data
    }
    
    static async saveProfessional(id, professional){
        const response = await axios.post(`${this.BASE_URL}/users/professional/${id}`, professional, {headers: this.getHeader()})
        return response.data
    }

    static async saveWorkFlow(boxOrders){
        const response = await axios.post(`${this.BASE_URL}/admin/box-order`, boxOrders, {headers: this.getHeader()})
        return response.data
    }

    static async getPersonalField() {
        try {
            const response = await axios.get(`${this.BASE_URL}/admin/personal`, {  headers: this.getHeader(), } );
            return response; // Return the response data
        } catch (error) {
            throw new Error("Failed to fetch personal fields : " + error.message);
        }
    }

    static async putPersonalField(fieldBox){
        const response = await axios.put( `${this.BASE_URL}/admin/update-personal`,{ personal_field: fieldBox }, { headers: this.getHeader() } );
        return response;
        
    }

    static async getProfessionalField(){
        try{
            const response = await axios.get(`${this.BASE_URL}/admin/professional`, { headers: this.getHeader() })
            return response;
        } catch (error){
            throw new Error("Server Error : " + error.message);
        }
    }


    static async putProfessionalField(fieldBox){
        try{
            const response = await axios.put(`${this.BASE_URL}/admin/update-professional`, {professional_field: fieldBox}, {headers: this.getHeader() } )
            return response;
        } catch (error){
            throw new Error("Failed to put error : " + error.message);
        }
    }


    static async getEducationalField(){
        try{
            const response = await axios.get(`${this.BASE_URL}/admin/educational`, { headers: this.getHeader() })
            return response;
        } catch (error){
            throw new Error("Server Error : " + error.message);
        }
    }

    static async putEducationalField(fieldBox){
        try{
            const response = await axios.put(`${this.BASE_URL}/admin/update-educational`, {educational_field: fieldBox}, {headers: this.getHeader() } )
            return response;
        } catch (error){
            throw new Error("Failed to Put error : " + error.message);
        }
    }
    
    static async getAllUsers(){
        try{
            const response = await axios.get(`${this.BASE_URL}/admin/all-users`,  {headers: this.getHeader()} )
            return response;
        } catch (error){
            throw  new Error("Failed to Update Candidate Status Server Error" + error.message);
        }
    }


    static async updateCandidateStatus(obj){
        try{
            const response = await axios.put(`${this.BASE_URL}/admin/user-status-update`, obj, {headers: this.getHeader()})
            console.log(response);
            return response;
            
        } catch (error){
            throw new Error("Failed Updated")
        }
    }

    static async getUserUploadDetails(id) {
        try {
            console.log("User's ID : ", id);
            
            const response = await axios.get(`${this.BASE_URL}/users/user-detail/${id}`, { headers: this.getHeader() });
            console.log("✅ User Upload Details Response:", response.data);
            // console.log("✅", response.data.uploadedDocuments);
            return response.data;
        } catch (error) {
            console.error("❌ Failed to fetch user details:", error.message);
            return null;
        }
    }
    

    static async uploadFile(id, file){
        const formData = new FormData();
        formData.append("file", file); // Append the file to FormData
        try {
            const response = await axios.post(`${this.BASE_URL}/users/upload-file/${id}`, formData, {  headers: this.getHeaderForFileUpload() });
            
            console.log(response);
            return response
          } catch (error) {
            console.error("Error uploading file:", error);
            throw error;
          }
    }

    static async allUserStatus(){
        try{
            const response = await axios.get(`${this.BASE_URL}/admin/all-user-status`, { headers: this.getHeader() });
            console.log(response);
            return response;
        } catch(error){
            console.error("Error Fetching All User Status")
        }
    }


    
    

    /**AUTHENTICATION CHECKER */
    static logout() {
        localStorage.removeItem('token')
        localStorage.removeItem('role')
    }

    static isAuthenticated() {
        const token = localStorage.getItem('token')
        return !!token
    }

    static isAdmin() {
        const role = localStorage.getItem('role')
        return role === 'ADMIN'
    }

    static isUser() {
        const role = localStorage.getItem('role')
        return role === 'USER'
    }


}