import { useState } from 'react'

import styles from '../components/chat.style.module.css'

import burger from '../assets/burger-menu-vector.png'

const Navigation = () => {

    const [ show, setShow ] = useState(false)

    const handleShow = () => setShow(!show)

    return (

        <div className="d-flex flex-row bg-primary mb-4 px-3 justify-content-end" style={{ width: "100%", height: "50px", boxShadow: "0px 5px 6px #0d2149c7" }} >
            <div className="d-flex flex-column" >
                <img height="50px" width="50px" src={burger} onClick={handleShow} style={{ cursor: "pointer" }} />
                {(show) ? 
                <div className={"d-flex flex-column p-1 bg-light rounded" + styles.popOut} style={{ position: "absolute", width: "150px", transform: "translateX(-100px) translateY(42px)", boxShadow: "2px 5px 6px #0d2149c7" }} >

                </div>
                : null
                }
            </div>
        </div>
    )

}

export default Navigation
