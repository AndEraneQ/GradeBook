import axios from "axios"

const API_URL = "http://localhost:8080/api/"

class AuthService {
  
  async login(email, password) {
    const newErrors = {};

    if (!email) newErrors.email = "Email is required";
    if (!password) newErrors.password = "Password is required";

    if (Object.keys(newErrors).length > 0) {
        throw { errors: newErrors };
    }

    try {
        const response = await axios.post(API_URL + "auth", {
            email,
            password
        });
        return response; 
    } catch (error) {
      const errorData = error.response.data;
      if (error.response.status === 401) {
        throw { errors: { general: errorData.message || 'Invalid credentials' } };
      } else {
        throw new Error('An error occurred. Please try again later.');
      }
    }
}
      
    logout(navigate) {
        localStorage.removeItem("user_data");
        navigate('/login', { state: { message: 'Logged out successfully!' } });
    }
}

export default new AuthService();