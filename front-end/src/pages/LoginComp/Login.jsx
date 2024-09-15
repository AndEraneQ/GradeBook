import React, { useState, useEffect } from 'react';
import AuthService from '../../Services/AuthService';
import { useLocation, useNavigate } from 'react-router-dom';
import styles from './Login.module.css';
import Header from '../../components/Header/Header';
import ResponseHandler from '../../components/ResponseHandler/ResponseHandler';

function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [errors, setErrors] = useState({ email: "", password: "", general: "" });
    const [error, setError] = useState("");
    const location = useLocation();
    const [response, setResponse] = useState(location.state?.message);
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            const response = await AuthService.login(email, password);
            localStorage.setItem('user_data', JSON.stringify(response.data));
            navigate("/home");
        } catch (error) {
            setError(error.response.data);
        }
    };

    return (
        <div className={styles.loginPage}>
            <Header text="Welcome in Gradebook" />
            <div className={styles.loginContainer}>
                {(response || error) && (
                    <div className={styles.responseContainer}>
                        <ResponseHandler response={response} error={error} setResponse={setResponse} setError={setError}/>
                    </div>
                )}
                <div className={styles.usersData}>
                    <p className={styles.textLabel}>Email:</p>
                    <input 
                        className={styles.loginInput} 
                        type="text" 
                        value={email} 
                        onChange={(e) => setEmail(e.target.value)} 
                    />
                    {errors.email && <div className={styles.textDanger}>{errors.email}</div>}

                    <p className={styles.textLabel}>Password:</p>
                    <input 
                        className={styles.loginInput} 
                        type="password" 
                        value={password} 
                        onChange={(e) => setPassword(e.target.value)} 
                    />
                    {errors.password && <div className={styles.textDanger}>{errors.password}</div>}
                </div>
                <button 
                    className={styles.loginButton}
                    onClick={handleLogin}>
                    Log in
                </button>
            </div>
        </div>
    );
}

export default Login;
