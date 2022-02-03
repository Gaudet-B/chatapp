import { useState } from 'react'

// import styles from '../components/chat.style.module.css'

import burger from '../assets/burger-menu-vector.png'


const Navigation = props => {

    const { loggedIn, setLoggedIn } = props

    const [ show, setShow ] = useState(false)

    const handleShow = () => setShow(!show)

    const handleLogout = () => {
        sessionStorage.clear()
        setLoggedIn(false)
        setShow(!show)
    }

    return (

        <div className="d-flex flex-row bg-primary mb-4 px-3 justify-content-end" style={{ width: "100%", height: "50px", boxShadow: "0px 5px 6px #0d2149c7" }} >
            {(loggedIn) ? 
            <div className="d-flex flex-column me-2" >
                <img height="50px" width="50px" src={burger} onClick={handleShow} style={{ cursor: "pointer" }} />
                {(show) ? 
                <div className="d-flex flex-column p-1 bg-light border border-light rounded" style={{ position: "absolute", width: "150px", height: "120px", transform: "translateX(-100px) translateY(45px)", boxShadow: "2px 4px 6px rgb(89, 89, 89)" }} >
                    <p onClick={handleLogout} className="link link-primary text-decoration-underline fw-bold text-end me-1 mt-2 mb-0" style={{ cursor: "pointer" }} >log out</p>
                    <p className="link link-primary text-decoration-none fw-bold text-end me-1 mt-2" style={{ cursor: "pointer" }} >about the creator</p>
                    <a className="ms-4 text-decoration-none" style={{ fontSize: "11pt" }} href="https://www.briangaudet.com" target="_blank">briangaudet.com</a>
                </div>
                : null
                }
            </div>
            : null }
        </div>
    )

}

export default Navigation
