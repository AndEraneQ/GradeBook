import React from "react";
import WelcomeHeader from "../WelcomeHeaderComp/WelcomeHeader";
import Card from "../CardComp/Card";
import { useNavigate } from 'react-router-dom';
import AuthService from "../../Services/AuthService";
import NavBarService from "../../Services/NavBarService";
import { getUser } from "../../utils/userUtils";


const AdminHomepage = () => {
    const navigate = useNavigate();
    const user = getUser();
    const name = `${user?.firstName || ''} ${user?.lastName || ''}`.trim() || 'User';
    
    const handleLogout = () => {
        AuthService.logout(navigate);
    };
    
    const handleCheckData = () => {
        navigate('/user-data');
    };

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