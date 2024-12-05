import {useState} from 'react';
import {useNavigate} from 'react-router-dom';
import {useAuth} from '../context/security/AuthContext';
import './LoginComponent.css';

export default function LoginComponent() {

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [showErrorMessage, setShowErrorMessage] = useState(false)
    const [missingCredentials, setMissingCredentials] = useState(false)
    const navigate = useNavigate()
    const authContext = useAuth()

    function handleUsernameChange(event) {
        setUsername(event.target.value)
        setShowErrorMessage(false)
        setMissingCredentials(false)
    }

    function handlePasswordChange(event) {
        setPassword(event.target.value)
        setShowErrorMessage(false)
        setMissingCredentials(false)
    }

    async function handleSubmit() {
        if (username && username.length && password && password.length) {
            if (await authContext.login(username, password)) {
                navigate(`/shop`)
            } else {
                setShowErrorMessage(true)
            }
        }
        else {
            setMissingCredentials(true)
        }
    }

    return (
        <div className="formGroup">
            <div className="m-3"><h1>Log in with your credentials please</h1></div>
            {showErrorMessage && <div>Authentication Failed. Please check your credentials.</div>}
            {missingCredentials && <div>Please fill your credentials.</div>}
            <div>
                <div className="m-3">
                    <label>Email:</label>
                    <input type="text" name="username" value={username} onChange={handleUsernameChange}/>
                </div>
                <div className="m-3">
                    <label>Password:</label>
                    <input type="password" name="password" value={password} onChange={handlePasswordChange}/>
                </div>
                <div>
                    <button type="button" className="btn btn-success" name="login" onClick={handleSubmit}>login</button>
                </div>
            </div>
        </div>
    )
}
