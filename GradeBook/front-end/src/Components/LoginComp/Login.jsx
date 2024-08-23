import React, { useEffect, useState } from 'react';
import AuthService from '../../Services/AuthService';
import {useLocation, useNavigate} from 'react-router-dom';
import './Login.css';

function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [errors, setErrors] = useState({ email: "", password: "", general: ""});
    const location = useLocation();
    const [logOutMessage,setLogOutMessage] = useState(location.state?.message);
    const navigate = useNavigate();

    useEffect(() => {
        if (logOutMessage) {
            const timer = setTimeout(() => setLogOutMessage(''), 3000);
            return () => clearTimeout(timer);
        }
    }, [logOutMessage]);
    
    function handleEmailChange(e) {
        setEmail(e.target.value);
    }

    function handlePasswordChange(e) {
        setPassword(e.target.value);
    }

    function resetLogOutCorrectlyMessage(){
        setLogOutMessage('');
    }
    
    const handleLogin = async () => {
        try {
            const response = await AuthService.login(email, password);
            localStorage.setItem('user_data', JSON.stringify(response.data));
            navigate("/home");
        } catch (error) {
            if (error.errors) {
                resetLogOutCorrectlyMessage();
                setErrors(error.errors);
            } else {
                setErrors({ general: 'An error occurred. Please try again later.' });
            }
        }
    }

    return (
        <>
            <div className='login-page'>
                <h1 className='welcome-text'>Welcome in Gradebook</h1>
                  <div className='login-container'>
                    <div className='logged-out-message'>
                    {logOutMessage && <p>{logOutMessage}</p>}
                    </div>
                    <div className='users-data'>
                        <p>Email:</p>
                        <input 
                            onChange={handleEmailChange} 
                            type='text' 
                            value={email}
                        />
                        {errors.email && <div className='text-danger'>{errors.email}</div>}
                        <p>Password:</p>
                        <input 
                            onChange={handlePasswordChange} 
                            type='password' 
                            value={password} 
                        />
                        {errors.password && <div className='text-danger'>{errors.password}</div>}
                        {errors.general && <div className='text-danger'>{errors.general}</div>}

                    </div>
                    <div className='button-link-container'>
                        <button 
                            className='login-button'
                            onClick={handleLogin}>
                            Log in
                        </button>
                    </div>
                </div>
            </div>
        </>
    );
}

export default Login;
