import React, { useState } from "react";
import './AllUsersPage.css';
import { useEffect } from "react";
import GoBackButton from "../../components/GoBackButton/GoBackButton";
import NavBarService from "../../Services/NavBarService";
import { useLocation, useNavigate } from "react-router-dom";
import NavBarNavigator from "../../components/NavBarNavigator/NavBarNavigator";
import Header from "../../components/Header/Header";
import Dots from "../../components/Dots/Dots";

function AllUsersPage(){

    const [allUsers,setAllUsers] = useState([]);
    const [filteredUsers, setFilteredUsers] = useState([]);
    const [error, setError] = useState(null);
    const location = useLocation();
    const [response,setResponse] = useState(location.state?.response);
    const navigate = useNavigate();
    

    const getAllUsers = async () => {
        try {
            const response = await NavBarService.getAllUsers();
            setAllUsers(response.data);
            setFilteredUsers(response.data);
            console.log(response.data);
        } catch (error) {
            console.error('There was an error fetching the subjects!', error);
            setError("Failed to fetch subjects. Please try again later.");
        }
    };

    const handleAddUser = () => {
        navigate("/register-user");
    }

    useEffect(() => {
        getAllUsers();
    }, []);

    const handleFilterUsers = (e) => {
        if(response){
            setResponse('');
        }
        const newUsers = allUsers.filter(user => {
            const matchToInput = user.firstName.toLowerCase() + " " 
                                 + user.lastName.toLowerCase() + " " 
                                 + user.email.toLowerCase() + " " 
                                 + user.role.toLowerCase();
            
            return matchToInput.startsWith(e.target.value);
        });
        setFilteredUsers(newUsers);
    }
    
    return (
        <div className="page-container">
            <NavBarNavigator/>
            <Header text="All users: "/>
            <div className="background-container">
                <div className="search-and-add-container">
                    <input 
                        className="search-input" 
                        type="text" 
                        placeholder="🔍 Search:"
                        onChange={handleFilterUsers}/>
                    <button 
                        className="confirm-button" 
                        onClick={handleAddUser}>
                            Add New User
                    </button>
                </div>
                        {response && (
                            <div className="response-part">
                                <div className="response-container">
                                    <p>{response}</p>
                                </div>
                            </div>
                        )}
                        {filteredUsers.length===0 && (
                            <div className="information-container">
                                {allUsers.length!==0 ? (
                                    <p>Type correct person.</p>
                                ) : (
                                    <p>There are no persons, you can add them now.</p>
                                )}
                            </div>
                        )}
                        <div className="display-items">
                            <ul className="single-item">
                                {filteredUsers.slice(0,7).map((user, index) => (
                                    <li key={user.id} className="list-item" onClick={() => navigate('/user-data', {state: {user: user}})}>
                                        {index + 1}.
                                        <b>First name:</b>  { user.firstName}&nbsp;
                                        <b>Last name:</b> { user.lastName}  &nbsp;
                                        <b>Email:</b> { user.email} &nbsp;
                                        <b>Role:</b> { user.role}
                                    </li>
                                ))}
                            </ul>
                        </div>
                        {filteredUsers.length>7 && (
                            <Dots/>
                        )}
                    </div>
            </div>
    );
}

export default AllUsersPage