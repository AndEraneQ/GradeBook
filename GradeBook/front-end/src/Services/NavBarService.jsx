import axios from "axios";
import { authHeader } from "./UserService";

const API_URL = "http://localhost:8080/user/";

class NavBarService {
    async getUserData(id) {
       return axios.get(API_URL + id, {headers: authHeader()});
  }
}
export default new NavBarService();
