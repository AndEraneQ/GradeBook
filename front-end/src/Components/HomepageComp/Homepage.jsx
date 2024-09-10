import React from "react";
import Card from "../CardComp/Card";
import { useNavigate } from 'react-router-dom';
import AuthService from "../../Services/AuthService";
import NavBarService from "../../Services/NavBarService";
import { getUser } from "../../utils/userUtils";
import './Homepage.css';


const Homepage = () => {
    const navigate = useNavigate();
    const user = getUser();
    const name = `${user?.firstName || ''} ${user?.lastName || ''}`.trim() || 'User';
    
    const handleLogout = () => {
        AuthService.logout(navigate);
    };
    
    const handleCheckData = () => {
        navigate('/user-data', {state: {fromHomePage: true}});
    };

    const handleManageSubjects = () => {
        navigate('/subjects');
    }

    const handleManageUsers = () => {
        navigate('/users');
    }

    const handleClasses = () => {
        navigate('/classes');
    }
    console.log(user);
    return (
        <div className="admin-homepage-container">
            <h1>Welcome {name}</h1>
            <div className="cards">
                {user.role==="ADMIN" && (
                    <>
                    <Card imagePath="src/assets/manageClasses.png" text="Manage Class" onClick={handleClasses}/>
                    <Card imagePath="src/assets/managePersons.png" text="Manage Persons" onClick={handleManageUsers}/>
                    <Card imagePath="src/assets/manageSubjects.png" text="Manage Subjects" onClick={handleManageSubjects}/>
                    </>
                )}
                {user.role==="TEACHER" && (
                    <>
                    {user.className!==null && (
                        <Card imagePath="src/assets/myClassPhoto.png" text="Your class" onClick={() => navigate('/my-class')}/>
                        )
                    }
                    <Card imagePath="src/assets/gradesPhoto.png" text="Manage grades" onClick={() => navigate('/choose-class')}/>
                    </>
                )}
                {user.role==="STUDENT" && (
                    <Card imagePath="src/assets/gradesPhoto.png" text="Your grades" onClick={() => navigate('/grades', {state: {user:user}})}/>
                )}
                <Card imagePath="src/assets/mailBoxPhoto.png" text="Mail" onClick={() => navigate('/mail-box')}/>
                <Card imagePath="src/assets/checkYourData.png" text="Check Your Data" onClick={handleCheckData}/>
                <Card imagePath="src/assets/Logout.png" text="Logout" onClick={handleLogout}/>
            </div>

        </div>
    );
}

export default Homepage;