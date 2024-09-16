import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { getUser } from "../../utils/userUtils";
import { faHouse, faUser, faClipboardList, faFileAlt, faBookOpen, faCog, faChalkboardTeacher, faSignOutAlt, faTasks, faEnvelope, faChartBar, faClipboard, faAddressCard, faPencil } from "@fortawesome/free-solid-svg-icons"; // Dodałem więcej ikon
import { useNavigate } from "react-router-dom";
import AuthService from "../../Services/AuthService";
import styles from './NavBarNavigator.module.css';

function NavBarNavigator() {
    const user = getUser();
    const navigate = useNavigate();

    const handleNavigate = (path) => {
        navigate(path);
    };

    return (
        <div className={styles.navBarNavigatorContainer}>
            <button onClick={() => handleNavigate('/home')}>
                <FontAwesomeIcon icon={faHouse} />
                <span> Home</span>
            </button>
            {user.role==="ADMIN" && (
                <>
                <button onClick={() => handleNavigate('/classes')}>
                <FontAwesomeIcon icon={faClipboard}/>
                    <span> Classes</span>
                </button>
                <button onClick={() => handleNavigate('/users')}>
                    <FontAwesomeIcon icon={faAddressCard} />
                    <span> Users</span>
                </button>
                <button onClick={() => handleNavigate('/subjects')}>
                    <FontAwesomeIcon icon={faBookOpen} />
                    <span> Subjects</span>
                </button>
                </>
            )}
            {user.role==="TEACHER" && (
                <>
                {user.className!==null && (
                    <button onClick={() => handleNavigate('/my-class')}>
                        <FontAwesomeIcon icon={faChalkboardTeacher} />
                        <span>My Class</span>
                    </button>
                )}
                <button onClick={() => handleNavigate('/choose-class')}>
                <FontAwesomeIcon icon={faPencil} />
                    <span>Manage Grades</span>
                </button>
                </>
            )}
            {user.role==="STUDENT" && (
                <>
                <button onClick={() => navigate('/grades', {state: {user:user}})}>
                    <FontAwesomeIcon icon={faFileAlt} />
                    <span>My Grades</span>
                </button>
                </>
            )}
            
            <button onClick={() => handleNavigate('/mail-box')}>
                <FontAwesomeIcon icon={faEnvelope} />
                <span>Mail Box</span>
            </button>
            <button onClick={() => navigate('/user-data', {state: {fromHomePage: true}})}>
                <FontAwesomeIcon icon={faUser} />
                <span>My Profile</span>
            </button>
            <button onClick={() => AuthService.logout(navigate)}>
                <FontAwesomeIcon icon={faSignOutAlt} />
                <span>Logout</span>
            </button>
        </div>
    );
}

export default NavBarNavigator;
