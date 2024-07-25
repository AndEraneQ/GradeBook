import axios from "axios"

const API_URL = "http://localhost:8080/auth/"

class AuthService {
    async login(email, password) {
        try {
            const response = await axios.post(API_URL + "login", {
              email,
              password
            });
            return response; 
          } catch (error) {
            throw error;
          }
        };
      
    logout() {
        localStorage.removeItem("user_data");
    }
}

export default new AuthService();