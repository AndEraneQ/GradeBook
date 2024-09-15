import React, { useState } from "react";
import './EditUserDataPage.css';
import GoBackButton from "../../components/GoBackButton/GoBackButton";
import { useLocation, useNavigate } from "react-router-dom";
import UserService from "../../Services/UserService";

function EditUserDataPage() {
    const navigate = useNavigate();
    const location = useLocation();
    const [wantChangePassword, setWantChangePassword] = useState(false);
    const [response,setResponse] = useState("");
    const [error,setError] = useState("");
    const user = location.state?.user;
    const residence = location.state?.residence;
    
    const [dataToChange,setDataToChange] = useState({
        userId: user.id,
        firstName: user.firstName,
        lastName: user.lastName,
        apartmentNumber: residence.apartmentNumber,
        buildingNumber: residence.buildingNumber,
        city: residence.city,
        street: residence.street,
        password: '',
        confirmPassword: ''
    })

    console.log(dataToChange);
    const toggleWantChangePassword = () => {
        setWantChangePassword(!wantChangePassword);
        setDataToChange(prevDataToChange => ({
            ...prevDataToChange,
            password: '',
            confirmPassword: ''
        }));
    }

    const handleDataChange = (e) => {
        const { name, value } = e.target;
        setDataToChange(prevDataToChange => ({
            ...prevDataToChange,
            [name]: value
        }));
    };

    const handleUpdateData = async () => {
        let resp = '';
        try{
            if(wantChangePassword && dataToChange.password=='' && dataToChange.confirmPassword==''){
            setError('You need to type password!')
            } else {
            setError('');
            resp = await UserService.editUserData(dataToChange);
            setResponse(resp.data);
            setWantChangePassword(false);
            }
        } catch (err){
            setResponse('');
            console.log(err.response.data);
            setError(err.response.data);
        }
        
    }

    return (
        <div className="edit-user-data-page">
            <GoBackButton/>
            <div className="edit-user-data-container">
                <div className="edit-user-data-header">
                    <h1>Edit Data:</h1>
                </div>
                <div className="edit-user-data-inputs">
                    <div className="input-group">
                        <p>First name:</p>
                        <input 
                            type="text"
                            name="firstName"
                            className="first-name-input" 
                            placeholder="Type first name"
                            value={dataToChange.firstName}
                            onChange={handleDataChange}/>
                    </div>
                    <div className="input-group">
                        <p>Last name:</p>
                        <input 
                            type="text"
                            name="lastName"
                            className="last-name-input" 
                            placeholder="Type last name"
                            value={dataToChange.lastName}
                            onChange={handleDataChange}/>
                    </div>
                    <div className="input-group">
                        <p>City:</p>
                        <input 
                            type="text"
                            name="city"
                            className="city-input" 
                            placeholder="Type city"
                            value={dataToChange.city}
                            onChange={handleDataChange}/>
                    </div>
                    <div className="input-group">
                        <p>Street:</p>
                        <input 
                            type="text"
                            name="street"
                            className="street-input" 
                            placeholder="Type street"
                            value={dataToChange.street}
                            onChange={handleDataChange}/>
                    </div>
                    <div className="input-group">
                        <p>Apartment number:</p>
                        <input 
                            type="number"
                            name="apartmentNumber"
                            className="apartament-number-input" 
                            placeholder="Type apartment number"
                            value={dataToChange.apartmentNumber}
                            onChange={handleDataChange}/>
                    </div>
                    <div className="input-group">
                        <p>Building number:</p>
                        <input 
                            type="number"
                            name="buildingNumber"
                            className="building-number-input" 
                            placeholder="Type building number"
                            value={dataToChange.buildingNumber}
                            onChange={handleDataChange}/>
                    </div>
                    <div className="checkbox-container">
                        <input 
                            type="checkbox" 
                            checked={wantChangePassword} 
                            onChange={toggleWantChangePassword} 
                        /> 
                        <span>Do you want to change password?</span>
                    </div>
                    {wantChangePassword && (
                        <div className="passwords-container">
                            <div className="input-group">
                                <p>Password:</p>
                                <input 
                                    type="password"
                                    className="password-input" 
                                    placeholder="Type new password"
                                    name="password"
                                    value={dataToChange.password} 
                                    onChange={handleDataChange}/>
                            </div>
                            <div className="input-group">
                                <p>Confirm password:</p>
                                <input 
                                    type="password"
                                    className="password-confirm" 
                                    placeholder="Confirm password"
                                    name="confirmPassword"
                                    value={dataToChange.confirmPassword} 
                                    onChange={handleDataChange} />
                            </div>
                        </div>
                    )}
                </div>
                {response && (
                    <div className="response-container">
                        <p>{response}</p>
                    </div>
                    )
                }
                {error && (
                    <div className="error-container">
                        <p>{error}</p>
                    </div>
                    )
                }
                <div className="buttons-container">
                    <button 
                        className="update-butt"
                        onClick={handleUpdateData}>
                            Update
                    </button>  
                </div>
            </div>
        </div>
    );
}

export default EditUserDataPage;
