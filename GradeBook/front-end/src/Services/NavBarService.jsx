import axios from "axios";
import { getUserDataFromLocalStorage } from "../utils/localStorageUtils";
import { getJwtTokenFromLocalStorage } from "../utils/localStorageUtils";

const API_URL = "http://localhost:8080/user"
const user_data = getUserDataFromLocalStorage();

class NavBarService{
    async getUserDataFromDatabase(navigate, id){
        try {
            const response = await axios.post("user/getData", {
              id
            });
            return response; 
          } catch (error) {
            throw error;
          }
        };
    }

export default new NavBarService();