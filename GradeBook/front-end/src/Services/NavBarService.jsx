import axios from "axios";
import { authHeader } from "./UserService";

const API_URL = "http://localhost:8080/api/";

class NavBarService {
    async getUserData(id) {
       return axios.get(API_URL + "user/" + id, {headers: authHeader()});
    }

    async getUserResidence(id){
      return axios.get(API_URL + "residence/" + id, {headers: authHeader()});
    }
}
export default new NavBarService();
