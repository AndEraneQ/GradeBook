import { authHeader } from "./UserService";
import axios from "axios";

const API_URL = "http://localhost:8080/api/";

class MailService{
    async sendEmail(request){
        return axios.post(API_URL + `send/mail`, request , {headers: authHeader()});
    }
    async getSentMails(email) {
        return axios.get(`${API_URL}sent-mails`, {
            params: { email },
            headers: authHeader()
        });
    }
    async getReceivedMails(email) {
        return axios.get(`${API_URL}received-mails`, {
            params: { email },
            headers: authHeader()
        });
    }
    async setMailOpen(mailId){
        return axios.put(`${API_URL}set/mail-read/${mailId}`, {} , {headers: authHeader()});
    }
}

export default new MailService;