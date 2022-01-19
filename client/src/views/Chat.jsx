import { useEffect, useState } from 'react'
import History from '../components/History'

import lightUsers from '../assets/users-icon-light.png'
import lightConvo from '../assets/conversation-icon-light.png'

import styles from '../components/chat.style.module.css'

const Chat = () => {

    const [ user, setUser ] = useState({})
    const [ loading, setLoading ] = useState(true)
    const [ webSocket, setWebSocket ] = useState()
    const [ messageText, setMessageText ] = useState()

    const handleClick = () => {
        return
    }

    const connect = id => {

        console.log(id)
        
        // let host = document.location.host
        let host = "http://localhost:8080"
        let pathname = document.location.pathname
        console.log(`host: ${host}, path: ${pathname}`)
        
        let ws = new WebSocket("ws://" +host  + pathname + "chat/" + id)
        console.log(ws)
        setWebSocket(ws)
        console.log(webSocket)

        webSocket.onmessage = e => {
            let log = document.getElementById("history")
            console.log(e.data)
            let message = JSON.parse(e.data)
            log.innerHTML += message.from + " : " + message.content + "\n"
        }
    }

    const handleSend = e => {
        e.preventDefault()

        // let content = document.getElementById("msg").value;
        let content = messageText
        let json = JSON.stringify({
            "content":content
        });
        webSocket.send(json)
    }

    const handleChange = e => setMessageText(e.target.value)

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
                setTimeout(() => connect(user.id), 1000)
            })
            .catch(err => {console.log(err)})
    }, [])

    return (
        <div className="d-flex flex-row justify-content-around bg-primary rounded p-1" style={{width: "90%", maxWidth: "1200px", margin: "auto", boxShadow: "2px 6px 5px #0d2149c7"}}>
            <div className="d-flex flex-column bg-dark m-1 py-1 rounded justify-content-start" style={{ width: "30%", height: "650px" }}>
                <div className="d-flex flex-row justify-content-start text-light mb-3">
                    {(!loading) ? <p className="display-6 mb-0" style={(user.displayName.length > 12) ? { transform: "translateX(-45px) scale(0.45)" } : { transform: "translateX(-25px) scale(0.6)" }} >@ {user.displayName}</p> : null }
                </div>
                <div className="border border-secondary rounded" style={{ width: "94%", height: "1px", margin: "0px auto" }} ></div>
                <div className={"text-light my-2 p-2 " + styles.conversations} >
                    <p className="text-secondary fw-bold ms-1" style={{ fontSize: "18pt" }} >conversations</p>
                    <div className="d-flex flex-column ms-2" >
                        <div id="" className="d-flex flex-row" style={{ cursor: "pointer" }} onClick={handleClick} ><img src={lightConvo} alt="" style={{ transform: "scale(0.6)" }} /> <p className="ms-2" style={{ fontSize: "12pt" }} > user_one, user_...</p> </div>
                        <div id="" className="d-flex flex-row" style={{ cursor: "pointer" }} onClick={handleClick} ><img src={lightConvo} alt="" style={{ transform: "scale(0.6)" }} /> <p className="ms-2" style={{ fontSize: "12pt" }} > user_two, user_...</p> </div>
                        <div id="" className="d-flex flex-row" style={{ cursor: "pointer" }} onClick={handleClick} ><img src={lightConvo} alt="" style={{ transform: "scale(0.6)" }} /> <p className="ms-2" style={{ fontSize: "12pt" }} > user_three, use...</p> </div>
                        <div id="" className="d-flex flex-row" style={{ cursor: "pointer" }} onClick={handleClick} ><img src={lightConvo} alt="" style={{ transform: "scale(0.6)" }} /> <p className="ms-2" style={{ fontSize: "12pt" }} > user_four, user...</p> </div>
                        <div id="" className="d-flex flex-row" style={{ cursor: "pointer" }} onClick={handleClick} ><img src={lightConvo} alt="" style={{ transform: "scale(0.6)" }} /> <p className="ms-2" style={{ fontSize: "12pt" }} > user_five, user...</p> </div>
                        <div id="" className="d-flex flex-row" style={{ cursor: "pointer" }} onClick={handleClick} ><img src={lightConvo} alt="" style={{ transform: "scale(0.6)" }} /> <p className="ms-2" style={{ fontSize: "12pt" }} > user_six, user_...</p> </div>
                    </div>
                </div>
                <div className="border border-secondary rounded" style={{ width: "94%", height: "1px", margin: "0px auto" }} ></div>
                <div className={"text-light my-2 p-2 " + styles.conversations} >
                    <div className="d-flex flex-row"><p className="text-secondary fw-bold ms-1" style={{ fontSize: "18pt" }} >chatters</p> <img src={lightUsers} alt="" height="25px" className="ms-3 mt-1" /> </div>
                    <div className="d-flex flex-column ms-3" >
                        <div id="" className="d-flex flex-row" style={{ cursor: "pointer" }} onClick={handleClick} > <p className="ms-2" style={{ fontSize: "12pt" }} > @ user_one</p> </div>
                        <div id="" className="d-flex flex-row" style={{ cursor: "pointer" }} onClick={handleClick} > <p className="ms-2" style={{ fontSize: "12pt" }} > @ user_two</p> </div>
                        <div id="" className="d-flex flex-row" style={{ cursor: "pointer" }} onClick={handleClick} > <p className="ms-2" style={{ fontSize: "12pt" }} > @ user_three</p> </div>
                        <div id="" className="d-flex flex-row" style={{ cursor: "pointer" }} onClick={handleClick} > <p className="ms-2" style={{ fontSize: "12pt" }} > @ user_four</p> </div>
                        <div id="" className="d-flex flex-row" style={{ cursor: "pointer" }} onClick={handleClick} > <p className="ms-2" style={{ fontSize: "12pt" }} > @ user_five</p> </div>
                        <div id="" className="d-flex flex-row" style={{ cursor: "pointer" }} onClick={handleClick} > <p className="ms-2" style={{ fontSize: "12pt" }} > @ user_six</p> </div>
                    </div>
                </div>
                <div className="border border-secondary rounded" style={{ width: "94%", height: "1px", margin: "0px auto" }} ></div>
            </div>
            <div className="d-flex flex-column justify-content-end rounded" style={{ width: "70%", height: "650px" }}>
                <History />
                <form id="messageInput" onSubmit={handleSend} className="form" >
                    <div className="d-flex flex-row justify-content-between">
                        <input onChange={handleChange} type="text" id="chatInput" name="message" className="form-control bg-dark text-light ms-1" style={{ width: "100%" }} />
                        <button type="submit" className="btn btn-outline-light ms-1 me-2" style={{ width: "60px" }} >send</button>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default Chat
