import { authHeader } from "./UserService";
import axios from "axios";

const API_URL = "http://localhost:8080/api/";

class SubjectService{
    async addSubject(name,listOfTeachers){
        return axios.post(API_URL + "add/subject",
                        {name: name, listOfTeachers: listOfTeachers},
                        {headers: authHeader()});
      }
}

export default new SubjectService;