import React, { useEffect, useState } from 'react';
import AuthService from '../../Services/AuthService';
import { useNavigate, useLocation} from 'react-router-dom';
import './Login.css';

function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [errors, setErrors] = useState({ email: "", password: "" });
    const location = useLocation();
    const [message,setMessage] = useState(location.state?.message);
    const navigate = useNavigate(); 
    
    function handleEmailChange(e) {
        setEmail(e.target.value);
    }

    function handlePasswordChange(e) {
        setPassword(e.target.value);
    }

    const validate = () => {
        const newErrors = {};
        if (!email) newErrors.email = "Email is required";
        if (!password) newErrors.password = "Password is required";
        setMessage('');
        return newErrors;
    };
    
    async function handleLogin() {
        const newErrors = validate();
        if (Object.keys(newErrors).length > 0) {
            setErrors(newErrors);
            return;
        }
        try {
            const response = await AuthService.login(email, password);
            if (response.status === 401) {
                setErrors({ password: "Invalid credentials" });
                setMessage('');
            } else {
                localStorage.setItem('user_data', JSON.stringify(response.data));
                const userRole = response.data.role;
                switch (userRole) {
                    case 'ROLE_TEACHER':
                        navigate('/teacher-homepage'); 
                        break;
                    case 'ROLE_STUDENT':
                        navigate('/student-homepage');
                        break;
                    case 'ROLE_ADMIN':
                        navigate('/admin-homepage');
                        break;
                    default:
                        setErrors({ general: 'Unknown role' });
                        break;
                }
            }
        } catch (error) {
          if (error.response && error.response.status === 401) {
              setErrors({ password: 'Invalid credentials' });
          } else {
              console.error('Error during login:', error);
          }
      }
  }

    return (
        <>
            <div className='login-page'>
                <h1 className='welcome-text'>Welcome in Gradebook</h1>
                  <div className='login-container'>
                    <div className='logged-out-message'>
                    {message && <p>{message}</p>}
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
