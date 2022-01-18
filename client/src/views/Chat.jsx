import { useEffect, useState } from 'react'
import History from '../components/History'

import lightUsers from '../assets/users-icon-light.png'
import lightConvo from '../assets/conversation-icon-light.png'

import styles from '../components/chat.style.module.css'

const Chat = () => {

    const [ user, setUser ] = useState({})
    const [ loading, setLoading ] = useState(true)

    const handleClick = () => {
        return
    }

    useEffect(() => {

        const reqOptions = {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                crossDomain: true,
                withCredentials: true
            }
        }

        fetch(`http://localhost:8080/api/getuser/${sessionStorage.getItem("id")}`)
            .then(res => res.json())
            .then(data => {
                setUser(data)
                setLoading(false)
            })
            .catch(err => {console.log(err)})
    }, [])

    return (
        <div className="d-flex flex-row justify-content-around bg-light rounded p-1" style={{width: "90%", maxWidth: "800px", margin: "auto", boxShadow: "2px 6px 5px #0d2149c7"}}>
            <div className="d-flex flex-column bg-dark m-1 py-1 rounded justify-content-start" style={{ width: "30%", height: "850px" }}>
                <div className="d-flex flex-row justify-content-start text-light mb-3">
                    {(!loading) ? <p className="display-6 mb-0" style={(user.displayName.length > 12) ? { transform: "translateX(-45px) scale(0.45)" } : { transform: "translateX(-25px) scale(0.6)" }} >@ {user.displayName}</p> : null }
                </div>
                <div className="border border-secondary rounded" style={{ width: "94%", height: "1px", margin: "0px auto" }} ></div>
                <div className={"text-light my-2 p-2 " + styles.conversations} >
                    <p className="text-secondary fw-bold ms-1" style={{ fontSize: "18pt" }} >conversations</p>
                    <div className="d-flex flex-column ms-2" >
                        <div id="" className="d-flex flex-row fs-5" style={{ cursor: "pointer" }} onClick={handleClick} ><img src={lightConvo} alt="" style={{ transform: "scale(0.8)" }} /> <p className="ms-2" > user_one, user_...</p> </div>
                        <div id="" className="d-flex flex-row fs-5" style={{ cursor: "pointer" }} onClick={handleClick} ><img src={lightConvo} alt="" style={{ transform: "scale(0.8)" }} /> <p className="ms-2" > user_two, user_...</p> </div>
                        <div id="" className="d-flex flex-row fs-5" style={{ cursor: "pointer" }} onClick={handleClick} ><img src={lightConvo} alt="" style={{ transform: "scale(0.8)" }} /> <p className="ms-2" > user_three, use...</p> </div>
                        <div id="" className="d-flex flex-row fs-5" style={{ cursor: "pointer" }} onClick={handleClick} ><img src={lightConvo} alt="" style={{ transform: "scale(0.8)" }} /> <p className="ms-2" > user_four, user...</p> </div>
                        <div id="" className="d-flex flex-row fs-5" style={{ cursor: "pointer" }} onClick={handleClick} ><img src={lightConvo} alt="" style={{ transform: "scale(0.8)" }} /> <p className="ms-2" > user_five, user...</p> </div>
                        <div id="" className="d-flex flex-row fs-5" style={{ cursor: "pointer" }} onClick={handleClick} ><img src={lightConvo} alt="" style={{ transform: "scale(0.8)" }} /> <p className="ms-2" > user_six, user_...</p> </div>
                    </div>
                </div>
                <div className="border border-secondary rounded" style={{ width: "94%", height: "1px", margin: "0px auto" }} ></div>
                <div className={"text-light my-2 p-2 " + styles.conversations} >
                    <div className="d-flex flex-row"><p className="text-secondary fw-bold ms-1" style={{ fontSize: "18pt" }} >chatters</p> <img src={lightUsers} alt="" height="30px" className="ms-3 mt-1" /> </div>
                    <div className="d-flex flex-column ms-3" >
                        <div id="" className="d-flex flex-row fs-5" style={{ cursor: "pointer" }} onClick={handleClick} > <p className="ms-2" > @ user_one</p> </div>
                        <div id="" className="d-flex flex-row fs-5" style={{ cursor: "pointer" }} onClick={handleClick} > <p className="ms-2" > @ user_two</p> </div>
                        <div id="" className="d-flex flex-row fs-5" style={{ cursor: "pointer" }} onClick={handleClick} > <p className="ms-2" > @ user_three</p> </div>
                        <div id="" className="d-flex flex-row fs-5" style={{ cursor: "pointer" }} onClick={handleClick} > <p className="ms-2" > @ user_four</p> </div>
                        <div id="" className="d-flex flex-row fs-5" style={{ cursor: "pointer" }} onClick={handleClick} > <p className="ms-2" > @ user_five</p> </div>
                        <div id="" className="d-flex flex-row fs-5" style={{ cursor: "pointer" }} onClick={handleClick} > <p className="ms-2" > @ user_six</p> </div>
                    </div>
                </div>
                <div className="border border-secondary rounded" style={{ width: "94%", height: "1px", margin: "0px auto" }} ></div>
            </div>
            <div className="d-flex flex-column justify-content-end rounded" style={{ width: "70%", height: "850px" }}>
                <History />
                <form>
                    <div className="d-flex flex-row justify-content-between">
                        <input type="text" id="chatInput" name="message" className="form-control bg-dark text-light" style={{ width: "100%" }} />
                        <button type="submit" className="btn btn-primary mx-1" style={{ width: "60px" }} >send</button>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default Chat
