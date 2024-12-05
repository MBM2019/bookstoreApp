import {useState, useEffect} from 'react';
import {useAuth} from '../context/security/AuthContext';
import './SigninComponent.css';

export default function SigninComponent() {

    const [firstname, setFirstname] = useState('')
    const [lastname, setLastname] = useState('')
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [missingCredentials, setMissingCredentials] = useState(false)
    const [userRegistered, setUserRegistered] = useState(false)
    const [registrationFailed, setRegistrationFailed] = useState(false)
    const authContext = useAuth()

    function handleUsernameChange(event) {
        setUsername(event.target.value)
        setMissingCredentials(false)
        setUserRegistered(false)
        setRegistrationFailed(false)
    }

    function handleFirstnameChange(event) {
        setFirstname(event.target.value)
        setMissingCredentials(false)
        setUserRegistered(false)
        setRegistrationFailed(false)
    }

    function handleLastnameChange(event) {
        setLastname(event.target.value)
        setMissingCredentials(false)
        setUserRegistered(false)
        setRegistrationFailed(false)
    }

    function handlePasswordChange(event) {
        setPassword(event.target.value)
        setMissingCredentials(false)
        setUserRegistered(false)
        setRegistrationFailed(false)
    }

    async function handleSubmit() {
        setUserRegistered(false)
        setRegistrationFailed(false)
        if (username && username.length && password && password.length
            && firstname && username.length && lastname && lastname.length) {
            if (await authContext.register(firstname, lastname, username, password)) {
                setUserRegistered(true)
            } else {
                setRegistrationFailed(true)
            }
        }
        else {
            setMissingCredentials(true)
        }
    }

    return (
        <div className="formGroup">
            <div className="m-3"><h1>Sign in with your credentials please.</h1></div>
            {registrationFailed && <div>Registration failed. Please check your credentials.</div>}
            {missingCredentials && <div>Please fill your credentials.</div>}
            {userRegistered && <div>The user has been registered. Please log in to start using the website.</div>}
            <div>
                <div className="m-3">
                    <label>Firstname:</label>
                    <input type="text" name="firstname" value={firstname} onChange={handleFirstnameChange}/>
                </div>
                <div className="m-3">
                    <label>Lastname:</label>
                    <input type="text" name="lastname" value={lastname} onChange={handleLastnameChange}/>
                </div>
                <div className="m-3">
                    <label>Email:</label>
                    <input type="text" name="username" value={username} onChange={handleUsernameChange}/>
                </div>
                <div className="m-3">
                    <label>Password:</label>
                    <input type="password" name="password" value={password} onChange={handlePasswordChange}/>
                </div>
                <div>
                    <button type="button" className="btn btn-success" name="login" onClick={handleSubmit}>sign in</button>
                </div>
            </div>
        </div>
    )
}
