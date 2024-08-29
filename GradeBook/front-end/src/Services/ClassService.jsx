import { authHeader } from "./UserService";
import axios from "axios";

const API_URL = "http://localhost:8080/api/";

class ClassService{
    async getAllClasses(){
        return axios.get(API_URL + "classes", {headers: authHeader()});
    }
    async addClass(className, teacher) {
        const data = {
            className: className,
            ...(teacher && { teacher })
        };
    
        return axios.post(API_URL + "add/class", data, {
            headers: authHeader()
        });
    }
    async getStudentLists(subjectId){
        return axios.get(API_URL + `find/students/${subjectId}`, {headers: authHeader()});
    }
}

export default new ClassService;