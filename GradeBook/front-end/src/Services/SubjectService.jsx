import { authHeader } from "./UserService";
import axios from "axios";

const API_URL = "http://localhost:8080/api/";

class SubjectService{
    async addSubject(name,listOfTeachers){
        return axios.post(API_URL + "add/subject",
                        {name: name, 
                        listOfTeachers: listOfTeachers},
                        {headers: authHeader()});
      }

    async editSubjectData(newSubject, teachersToRemove, teachersToAdd) {
        try {
            const response = await axios.put(API_URL + "edit/subjectData", {
                subject: newSubject,
                deletedTeachers: teachersToRemove,
                addedTeachers: teachersToAdd
            }, {
                headers: authHeader()
            });
            return response; 
        } catch (error) {
            throw error; 
        }
    }

    async deleteSubject(id){
        return axios.delete(API_URL + "remove/subject/" + id,
                            {headers: authHeader()});
    }
    
}

export default new SubjectService;