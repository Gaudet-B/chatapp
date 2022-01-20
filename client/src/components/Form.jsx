import { useState } from 'react'

import axios from 'axios'


const Form = props => {

    const { setLoggedIn, setLoading } = props

    const [ register, setRegister ] = useState(false)
    const [ formState, setFormState ] = useState({})

    const instance = axios.create({
        withCredentials: true,
        baseURL: "http://localhost:8080/api",
        crossDomain: true,
        
        // contentType: "application/json; charset=utf-8"
        // contentType: "application/json"
        // header: {
        //     method: "POST",
        //     contentType: "application/json"
        // }
    })

    // const navigate = useNavigate

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

        const reqOptions = {
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
                // setLoading(true)
                // console.log(data)
                setTimeout(() => {
                    sessionStorage.setItem("loggedIn", JSON.stringify(true))
                    sessionStorage.setItem("id", data.id)
                    setLoggedIn(true)
                }, 1500);
            })
            .catch(err => {console.log(err)})
            
        // navigate("/chat")
        // if (check) navigate("/chat")
    }

    const handleRegister = e => {
        e.preventDefault()

        const reqOptions = {
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
                // setLoading(true)
                setTimeout(() => {
                    setLoggedIn(true)
                    sessionStorage.setItem("id", data.id)
                    sessionStorage.setItem("loggedIn", JSON.stringify(true))
                }, 1500);
                // navigate("/chat")
            })
            .catch(err => console.log(err))


        // let jsonObj = JSON.stringify(formState)
        // console.log(jsonObj)
        // 
        // axios.post("http://localhost:8080/api/register", {
        //     withCredentials: true,
        //     crossDomain: true,
        //     contentType: "application/json",
        //     data: formState
        // })
        //     .then(res => {
        //         console.log(res.data)
        //         navigate("/chat")
        //     })
            
        // instance.post("/register", {data: jsonObj})
        // instance.post("/register", jsonObj)
        //     .then(res => {
            //         console.log(res.data)
            //         navigate("/chat")
            //     })
            //     .catch(err => {
                //         const {errors} = err.response.data
                //         let errObj = {}
                //         for (const [key, value] of Object.entries(errors)) {
                    //             errObj[key] = value.message
                    //         }
                    //         console.log(err.response.data)
                    //     })

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
