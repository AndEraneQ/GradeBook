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
      
    logout(navigate) {
        localStorage.removeItem("user_data");
        navigate('/login', { state: { message: 'Logged out successfully!' } });
    }
}

export default new AuthService();