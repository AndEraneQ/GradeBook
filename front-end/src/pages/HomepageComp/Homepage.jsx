import React from "react";
import Card from "../CardComp/Card";
import { useNavigate } from 'react-router-dom';
import AuthService from "../../Services/AuthService";
import { getUser } from "../../utils/userUtils";
import styles from './Homepage.module.css';
import Header from "../../components/Header/Header";

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

    return (
        <div className={styles.HomepageContainer}>
            <Header text={`Welcome, ${name}`} />
            <h2 className={styles.HomepageInstructions}>
                Choose what you want to do:
            </h2>
            <div className={styles.Cards}>
                {user.role === "ADMIN" && (
                    <>
                        <Card imagePath="src/assets/manageClasses.png" text="Manage Class" onClick={handleClasses} />
                        <Card imagePath="src/assets/managePersons.png" text="Manage Users" onClick={handleManageUsers} />
                        <Card imagePath="src/assets/manageSubjects.png" text="Manage Subjects" onClick={handleManageSubjects} />
                    </>
                )}
                {user.role === "TEACHER" && (
                    <>
                        {user.className && (
                            <Card imagePath="src/assets/myClassPhoto.png" text="Your Class" onClick={() => navigate('/my-class')} />
                        )}
                        <Card imagePath="src/assets/gradesPhoto.png" text="Manage Grades" onClick={() => navigate('/choose-class')} />
                    </>
                )}
                {user.role === "STUDENT" && (
                    <Card imagePath="src/assets/gradesPhoto.png" text="Your Grades" onClick={() => navigate('/grades', { state: { user: user } })} />
                )}
                <Card imagePath="src/assets/mailBoxPhoto.png" text="Mail" onClick={() => navigate('/mail-box')} />
                <Card imagePath="src/assets/checkYourData.png" text="Check Your Data" onClick={handleCheckData} />
                <Card imagePath="src/assets/Logout.png" text="Logout" onClick={handleLogout} />
            </div>
        </div>
    );
};

export default Homepage;
