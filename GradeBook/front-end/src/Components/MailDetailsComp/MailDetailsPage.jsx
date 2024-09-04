import React, { useEffect, useState } from "react";
import "./MailDetailsPage.css";
import GoBackButton from "../GoBackButtonComp/GoBackButton";
import { useLocation } from "react-router-dom";
import MailService from "../../Services/MailService";

function MailDetailsPage(){

    const location = useLocation();
    const mail = location.state?.mail;
    console.log(mail);

    useEffect(() => {
        const setMessageWasReaded = async () => {
            try{
                await MailService.setMailOpen(mail.id);
            } catch(err){
                console.log(err);
            }
        }
        setMessageWasReaded();
    })

    return (
        <div className="mail-details-page">
            <GoBackButton path = '/mail-box'/>
            <div className="mail-details-container">
                <p>From: {mail.fromUserEmail}</p>
                <p>To: {mail.toUserEmail}</p>
                <p>Date:{" "}
                        {new Date(mail.sentAt).toLocaleDateString()}{" "}
                        {new Date(mail.sentAt).toLocaleTimeString()}</p>
                <p>Subject: {mail.subject}</p>
                <p>Content: {mail.content}</p>
            </div>
        </div>
    );
}

export default MailDetailsPage;