import React, { useState } from "react";
import GoBackButton from "../../components/GoBackButton/GoBackButton";
import './RegisterPage.css';
import AuthService from "../../Services/AuthService";

function RegisterPage(){

    const [error,setError] = useState('');
    const [response,setResponse] = useState('');

    const [user, setUser] = useState({
        firstName: '',
        lastName: '',
        email: '',
        role: "STUDENT",
        className: ''
    })

    const [residence, setResidence] = useState({
        city: '',
        street: '',
        apartmentNumber: '',
        buildingNumber: '',
    })

    const handleUserDataChange = (e) => {
        const { name, value } = e.target;
        setUser((prevUser) => ({
          ...prevUser,
          [name]: value
        }));
    };

    const handleResidenceChange = (e) => {
        const { name, value } = e.target;
        setResidence((prevResidence) => ({
          ...prevResidence,
          [name]: name === 'apartmentNumber' || name === 'buildingNumber' ? (value === '' ? '' : parseInt(value)) : value
        }));
    };

    function areAllFieldsFilled(formData, fieldToSkip) {
        return Object.entries(formData).every(([key, value]) => {
            if (fieldToSkip && key === fieldToSkip) return true; 
            return value !== ''; 
        });
    }

    const handleRegisterUser = async () => {
        setResponse('');
        const userIsCorrect = user.role === 'STUDENT' ? areAllFieldsFilled(user) : areAllFieldsFilled(user,'className');
        if(userIsCorrect && areAllFieldsFilled(residence)){
            setError('');
            try{
                const response = await AuthService.register(user,residence);
                setResponse(response.data);
            } catch (err){
                console.log("coudn't register user");
                setResponse('');
                setError(err.response.data);
            }

        } else {
            setResponse('');
            setError('You need to complete all fields!')
        }
    }

    return(
        <div className="register-container">
            <GoBackButton path='/users'/>
            <div className="register-page">
                <div className="header-container">
                    <h1>Type user data</h1>
                </div>
                <div className="input-container">
                    <select 
                        id="options" 
                        name="role"
                        value={user.role} 
                        onChange={handleUserDataChange}
                        className="select">
                            <option value="STUDENT">Student</option>
                            <option value="TEACHER">Teacher</option>
                            <option value="ADMIN">Admin</option>
                    </select>
                    <input 
                        type="text"
                        name="firstName" 
                        value={user.firstName} 
                        onChange={handleUserDataChange}
                        placeholder="First Name:"/>
                    <input 
                        type="text" 
                        name="lastName" 
                        value={user.lastName} 
                        onChange={handleUserDataChange}
                        placeholder="Last Name:"/>
                    <input 
                        type="email" 
                        name="email" 
                        value={user.email} 
                        onChange={handleUserDataChange}
                        placeholder="Email:"/>
                    {user.role==="STUDENT" && (
                        <input 
                            type="text" 
                            name="className"
                            value={user.className}
                            onChange={handleUserDataChange}
                            placeholder="Class Name:"/>
                    )}
                    <input 
                        type="text"
                        name="city" 
                        value={residence.city} 
                        onChange={handleResidenceChange} 
                        placeholder="City:"/>
                    <input 
                        type="text" 
                        name="street" 
                        value={residence.street} 
                        onChange={handleResidenceChange}
                        placeholder="Street:"/>
                    <input 
                        type="number"
                        name="apartmentNumber" 
                        value={residence.apartmentNumber} 
                        onChange={handleResidenceChange} 
                        placeholder="Apartment Number:"/>
                    <input 
                        type="number" 
                        name="buildingNumber" 
                        value={residence.buildingNumber} 
                        onChange={handleResidenceChange}
                        placeholder="Building Number:"/>
                </div>
                <div className="information-container">

                
                {error && (
                    <div className="error-container">
                        <p>{error}</p>
                    </div>
                )}
                {response && (
                    <div className="response-container">
                        <p>{response}</p>
                    </div>
                )}
                </div>
                <div className="register-button">
                    <button 
                    className="register"
                    onClick={handleRegisterUser}>                   
                        Register
                    </button>
                </div>
            </div>
        </div>
    );
}

export default RegisterPage;