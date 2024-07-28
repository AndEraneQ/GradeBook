import React from "react";
import WelcomeHeader from "../WelcomeHeaderComp/WelcomeHeader";
import Card from "../CardComp/Card";
import { useNavigate } from 'react-router-dom';
import AuthService from "../../Services/AuthService";
import { getUserDataFromLocalStorage } from "../../utils/localStorageUtils";
import NavBarService from "../../Services/NavBarService";

function AdminHomepage() {
    const navigate = useNavigate();
    const user = getUserDataFromLocalStorage();
    const name = user
        ? `${user.firstName || ''} ${user.lastName || ''}`
        : 'User';

    function handleLogout(){
        AuthService.logout(navigate);
    }

    function handleCheckData(){
        NavBarService.getUserDataFromDatabase(navigate)

    }

    return (
        <div className="admin-homepage-container">
            <WelcomeHeader name={name} />
            <div className="cards">
                <Card imagePath="src/assets/manageClasses.png" text="Manage Class"/>
                <Card imagePath="src/assets/managePersons.png" text="Manage Persons"/>
                <Card imagePath="src/assets/manageSubjects.png" text="Manage Subjects"/>
                <Card imagePath="src/assets/checkYourData.png" text="Check Your Data" onClick={handleCheckData}/>
                <Card imagePath="src/assets/Logout.png" text="Logout" onClick={handleLogout}/>
            </div>

        </div>
    );
}

export default AdminHomepage;