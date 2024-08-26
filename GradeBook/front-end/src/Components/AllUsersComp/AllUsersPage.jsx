import React, { useState } from "react";
import './AllUsersPage.css';
import { useEffect } from "react";
import GoBackButton from "../GoBackButtonComp/GoBackButton";
import NavBarService from "../../Services/NavBarService";
import { useNavigate } from "react-router-dom";

function AllUsersPage(){

    const [allUsers,setAllUsers] = useState([]);
    const [filteredUsers, setFilteredUsers] = useState([]);
    const [error, setError] = useState(null);
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
        <div className="all-users-page">
            <GoBackButton path='/home'/>
            
            <div className="all-users-container">
                <div className="header-container">
                    <h1>Users:</h1>
                </div>
                {error ? (
                    <div className="error-message">{error}</div>
                ) : (
                    <div>
                        <div className="search-and-add-container">
                            <input 
                                className="search-input" 
                                type="text" 
                                placeholder="🔍 Search:"
                                onChange={handleFilterUsers}/>
                            <button 
                                className="add-user-button" 
                                onClick={handleAddUser}>
                                    Add New User
                            </button>
                        </div>
                        {filteredUsers.length===0 && (
                            <div className="information-container">
                                {allUsers.length!==0 ? (
                                    <p>Type correct person.</p>
                                ) : (
                                    <p>There are no persons, you can add them now.</p>
                                )}
                            </div>
                        )}
                            <ul>
                            {filteredUsers.slice(0,10).map((user, index) => (
                                <li key={user.id} className="user-item">
                                    {index + 1}.
                                    First name: <b> { user.firstName}</b>&nbsp;
                                    Last name: <b>{ user.lastName}</b>  &nbsp;
                                    Email: <b>{ user.email}</b> &nbsp;
                                    Role: <b>{ user.role}</b>
                                </li>
                            ))}
                        </ul>
                    </div>
                )}
            </div>
        </div>
    );
}

export default AllUsersPage