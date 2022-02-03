import { useState } from 'react'


const Form = props => {

    const { setLoggedIn } = props

    const [ register, setRegister ] = useState(false)
    const [ formState, setFormState ] = useState({})

    const handleShowRegister = e => {
        e.preventDefault()
        setRegister(true)
    }
    const handleHideRegister = e => {
        e.preventDefault()
        setRegister(false)
    }

    const handleChange = e => {
        setFormState({
            ...formState,
            [e.target.name]: e.target.value
        })
    }

    const handleLogin = e => {
        e.preventDefault()

        let reqOptions = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                crossDomain: true,
                withCredentials: true
            },
            body: JSON.stringify(formState)
        }

        fetch("http://localhost:8080/api/login", reqOptions)
            .then(res => res.json())
            .then(data => {
                console.log(data)
                setTimeout(() => {
                    sessionStorage.setItem("id", data.id)
                    if (data.id > 0) {
                        sessionStorage.setItem("loggedIn", JSON.stringify(true))
                        setLoggedIn(true)
                    }
                    if (data.id === undefined) {
                        sessionStorage.setItem("loggedIn", JSON.stringify(false))
                        setLoggedIn(false)
                    }
                }, 1000);
            })
            .catch(err => {console.log(err)})
    }

    const handleRegister = e => {
        e.preventDefault()

        let reqOptions = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                crossDomain: true,
                withCredentials: true
            },
            body: JSON.stringify(formState)
        }

        fetch("http://localhost:8080/api/register", reqOptions)
            .then(res => res.json())
            .then(data => {
                setTimeout(() => {
                    sessionStorage.setItem("id", data.id)
                    sessionStorage.setItem("loggedIn", JSON.stringify(true))
                    setLoggedIn(true)
                }, 1500);
            })
            .catch(err => console.log(err))
    }

    return (

        <div>
            <form id="form" method="post" onSubmit={(!register) ? handleLogin : handleRegister } className="form ms-4 px-4">
                <div className="d-flex flex-row form-group justify-content-between my-2">
                    <label id="email_label" className="form-label fs-5" htmlFor="email">email address:</label>
                    <input id="email_input" className="form-control me-1" name="email" type="email" onChange={handleChange} style={{ maxWidth: "400px", maxHeight: "38px" }} placeholder="email@email.com" />
                </div>
                <div className="d-flex flex-row form-group justify-content-between my-2">
                    <label id="password_label" className="form-label fs-5" htmlFor="password">password:</label>
                    <input id="password_input" className="form-control me-1" name="password" type="password" onChange={handleChange} style={{ maxWidth: "400px", maxHeight: "38px" }} placeholder="password" />
                </div>
                {(register) ? 
                <div>
                    <div className="d-flex flex-row form-group justify-content-between my-2">
                        <label id="confirm_password_label" className="form-label fs-5" htmlFor="confirmPassword">confirm password:</label>
                        <input id="confirm_password_input" className="form-control me-1" name="confirmPassword" type="password" onChange={handleChange} style={{ maxWidth: "400px", maxHeight: "38px" }} placeholder="confirm password" />
                    </div>
                    <div className="d-flex flex-row form-group justify-content-between my-2">
                        <label id="displayName_label" className="form-label fs-5" htmlFor="displayName">display name:</label>
                        <input id="displayName_input" className="form-control me-1" name="displayName" type="text" onChange={handleChange} style={{ maxWidth: "400px", maxHeight: "38px" }} placeholder="how your name will display in chat" />
                    </div>
                </div>
                : null
                }
                <div className="d-flex flex-row justify-content-end">
                    <div className="d-flex flex-column me-1" style={{ width: "100%", maxWidth: "400px" }}>
                        <button type="submit" className="btn btn-primary">{(!register) ? "login" : "register" }</button>
                        <button onClick={(!register) ? handleShowRegister : handleHideRegister } className="btn btn-outline-light my-2">{(!register) ? "create an account" : "back to login" } </button>
                    </div>
                </div>
            </form>

        </div>

    )
}

export default Form
