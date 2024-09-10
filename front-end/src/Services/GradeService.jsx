import { authHeader } from "./UserService";
import axios from "axios";

const API_URL = "http://localhost:8080/api/";

class GradeService{
    async getGradesForStudentBySubject(userId,subjectId){
        return axios.get(API_URL + `grades/user/${userId}/subject/${subjectId}`, {headers: authHeader()});
    }
    async editGrade(grade){
        return axios.put(API_URL + `edit/grade`, grade, {
            headers: authHeader()
        })
    }
    async addGrade(grade){
        return axios.post(API_URL + `add/grade`, grade, {
            headers: authHeader()
        })
    }
    async deleteGrade(gradeId){
        return axios.delete(API_URL + `delete/grade/${gradeId}`, {headers: authHeader()});
    }
}

export default new GradeService;