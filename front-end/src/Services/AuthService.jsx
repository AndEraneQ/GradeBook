import axios from "axios"
import { authHeader } from "./UserService";

const API_URL = "http://localhost:8080/api/"

class AuthService {
  
  async login(email, password) {
    
    return await axios.post(API_URL + "auth", {
      email,
      password
  });
}
      
    logout(navigate) {
        localStorage.removeItem("user_data");
        navigate('/login', { state: { message: 'Logged out successfully!' } });
    }

    async register(user,residence){
      return axios.post(API_URL + 'register', {
        user: user,
        residence: residence
      }, {
        headers: authHeader()
      });
    }
}

export default new AuthService();