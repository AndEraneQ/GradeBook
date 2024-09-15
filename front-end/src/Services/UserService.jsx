import axios from "axios";
//import { useNavigate } from "react-router-dom";

const API_URL = "http://localhost:8080/api/";

export function authHeader() {
    const user = JSON.parse(localStorage.getItem('user_data'));

    if (user && user.token) {
      return { Authorization: 'Bearer ' + user.token };
    } else {
      return {};
    }
}

class UserService{
  async deleteUser(id) {
    return axios.delete(API_URL + 'delete/user/' + id, {
        headers: authHeader(),
    });
  }
  async editUserData(dataToChange){
    return axios.put(API_URL + 'edit/user', dataToChange, {
      headers: authHeader(),
    })
  }
  async getStudents(){
    return axios.get(API_URL + 'students', {headers: authHeader()});
  }
  async getTeacher(userId){
    return axios.get(API_URL + `teacher/${userId}`, {headers: authHeader()});
  }

}

export default new UserService();
