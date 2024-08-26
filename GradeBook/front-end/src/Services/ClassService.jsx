import { authHeader } from "./UserService";
import axios from "axios";

const API_URL = "http://localhost:8080/api/";

class ClassService{
    async getAllClasses(){
        return axios.get(API_URL + "classes", {headers: authHeader()});
    }
}

export default new ClassService;