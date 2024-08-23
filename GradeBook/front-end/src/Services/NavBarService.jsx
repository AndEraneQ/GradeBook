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

    async getSubjects(){
      return axios.get(API_URL + "subjects", {headers: authHeader()});
    }

    async getAllUsers() {
      return axios.get(API_URL + "users", {headers: authHeader()});
   }
   
    async getAllUsersByRole(role){
      return axios.post(API_URL + "users/byRoles", {name: role}, {headers: authHeader()});
    }

    async getAllTeachersOfSubject(id){
      return axios.get(API_URL + 'teachers/subject/' + id, {headers: authHeader()});
    }

    async getAllTeachers(){
      return axios.get(API_URL + 'teachers', {headers: authHeader()});
    }
}
export default new NavBarService();
