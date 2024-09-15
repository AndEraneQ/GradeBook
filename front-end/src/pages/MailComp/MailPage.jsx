import React, { useEffect, useState } from "react";
import "./MailPage.css";
import GoBackButton from "../../components/GoBackButton/GoBackButton";
import { getUser } from "../../utils/userUtils";
import MailService from "../../Services/MailService";
import { useNavigate } from "react-router-dom";
import ResponseHandler from "../../components/ResponseHandler/ResponseHandler";

function MailPage() {
    const [view, setView] = useState("received");
    const navigate = useNavigate();
    const user = getUser();
    const [email,setEmail] = useState('');
    const [subject,setSubject] = useState('');
    const [content, setContent] = useState('');
    const [error,setError] = useState('');
    const [response,setResponse] = useState('');
    const [sentMails, setSentMails] = useState([]);
    const [receivedEmails, setReceivedEmails] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            const sentMailsFromApi = await MailService.getSentMails(user.email);
            const receivedMailsFromApi = await MailService.getReceivedMails(user.email);
            setReceivedEmails(receivedMailsFromApi.data);
            setSentMails(sentMailsFromApi.data);
            console.log(sentMails);
            console.log(receivedEmails);
        }
        fetchData();
    }, [view]);

    const clearFields = () => {
        setEmail('');
        setContent('');
        setSubject('');
    }

    const clearMessages = () => {
        setError('');
        setResponse('');
    }

    const handleSendEmail = async () => {
        clearMessages();
        if(email==='' || subject === '' || content === ''){
            setError("You need to complete all fields!");
            return;
        }

        const request = {
            fromUserEmail: user.email,
            toUserEmail: email,
            subject: subject,
            content: content
        }

        try{
            const response = await MailService.sendEmail(request);
            clearFields();
            setResponse(response.data);
        } catch(err){
            setError(err.response.data);
        }
    }

    const displayEmails = (mails) => {
        return (
            <div className="emails-container">
                {mails.map((mail, index) => (
                    <div className="email-container" key={index} onClick={() => navigate('/mail-details', {state: {mail: mail}})}>
                    <p>
                        Subject: {mail.subject} &nbsp; &nbsp; &nbsp; Date:{" "}
                        {new Date(mail.sentAt).toLocaleDateString()}{" "}
                        {new Date(mail.sentAt).toLocaleTimeString()}
                    </p>
                    <p>
                        From: {mail.fromUserEmail} &nbsp; &nbsp; &nbsp; To:{" "}
                        {mail.toUserEmail}
                    </p>
                </div>
                ))}
            </div>
        );
    }

    

    const renderView = () => {
        switch (view) {
            case "received":
                return displayEmails(receivedEmails)
            case "sent":
                return displayEmails(sentMails)
            case "new":
                return (
                    <div className="send-email-container">
                        <ResponseHandler response={response} error={error}/>
                        <p>Type email</p>
                        <input 
                            placeholder="Email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}/>
                        <p>Type subject</p>
                        <input 
                            placeholder="Subject"
                            value={subject}
                            onChange={(e) => setSubject(e.target.value)}/>
                        <p>Type content</p>
                        <textarea
                            className="email-input"
                            rows="10" 
                            placeholder="Write your email here..." 
                            value={content}
                            onChange={(e) => setContent(e.target.value)}
                        />
                        <button onClick={handleSendEmail}>Send</button>
                    </div>
                );
            default:
                return null;
        }
    };

    return (
        <div className="mail-page">
            <GoBackButton path="/home" />
            <div className="mail-container">
                <div className="button-container">
                    <button onClick={() => setView("received")}>Received Mails</button>
                    <button onClick={() => setView("sent")}>Sent Mails</button>
                    <button onClick={() => setView("new")}>New Mail</button>
                </div>
                <div className="view-container">
                    {renderView()}
                </div>
            </div>
        </div>
    );
}

export default MailPage;
