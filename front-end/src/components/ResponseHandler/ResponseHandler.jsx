import React, { useEffect } from 'react';
import styles from './ResponseHandler.module.css';

const ResponseHandler = ({ response, error, setResponse, setError }) => {
    useEffect(() => {
        if (response || error) {
            const timer = setTimeout(() => {
                setResponse('');
                setError('');
            }, 3000); 

            return () => clearTimeout(timer);
        }
    }, [response, error, setResponse, setError]);

    return (
        <div className={styles.responseContainer}>
            {error && <p className={styles.responseError}>{error}</p>}
            {response && !error && <p className={styles.responseSuccess}>{response}</p>}
        </div>
    );
};

export default ResponseHandler;
