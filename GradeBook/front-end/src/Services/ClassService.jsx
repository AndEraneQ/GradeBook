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
    async deleteClass(classId){
        return axios.delete(API_URL + `delete/classroom/${classId}`, {headers: authHeader()});
    }
    async editClassData(classroom,students){
        return axios.put(API_URL + 'edit/classroom', {
            classroomDto: classroom,
            students: students
        }, {
            headers: authHeader()
        })
    }
}

export default new ClassService;