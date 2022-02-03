import { useEffect, useState } from 'react'

import History from '../components/History'
import Loading from '../components/Loading'

import lightUsers from '../assets/users-icon-light.png'
import lightConvo from '../assets/conversation-icon-light.png'

import styles from '../components/chat.style.module.css'

const Chat = props => {

    const [ loading, setLoading ] = useState(true)
    const [ user, setUser ] = useState({})
    const [ allChatters, setAllChatters ] = useState([])
    const [ currentChatter, setCurrentChatter ] = useState()
    const [ chatting, setChatting ] = useState(false)
    const [ activeThread, setActiveThread ] = useState(false)
    const [ threads, setThreads ] = useState([])
    
    const handleNewThread = (creator, user, options) => {
    
        fetch(`http://localhost:8080/api/newthread/${creator}/${user}`, options) // <-- create a new thread between the logged in user (session.id) and the passed-in {id} 
            .then(res => res.json())
            .then(data => {
                setActiveThread(data)
                console.log(data)
                setChatting(true)
            })
            .catch(err => console.log(err))
    }

    const handleClick = id => {

        setActiveThread(false)
        setChatting(false)

        console.log(id)

        const reqOptions = {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                crossDomain: true,
                withCredentials: true
            }
        }

        console.log(`http://localhost:8080/api/fetchthread/${sessionStorage.getItem("id")}/${id}`)

        fetch(`http://localhost:8080/api/fetchthread/${sessionStorage.getItem("id")}/${id}`, reqOptions)
            .then(res => res.json())
            .then(data => setActiveThread(data))
            .then(() => {
                setCurrentChatter(id)
                setChatting(true)
            })
            .catch(err => {
                handleNewThread(sessionStorage.getItem("id"), id, reqOptions)
            })
    }
    
    const handleSend = e => {
        e.preventDefault()
    }

    var otherUsers

    useEffect(() => {

        fetch(`http://localhost:8080/api/getallusers`)
            .then(res => res.json())
            .then(data => setAllChatters(data))
            .then(
                fetch(`http://localhost:8080/api/getuser/${sessionStorage.getItem("id")}`)
                    .then(res => res.json())
                    .then(data => setUser(data))
                    .then(
                        fetch(`http://localhost:8080/api/getthreadsbyuser/${sessionStorage.getItem("id")}`)
                            .then(res => res.json())
                            .then(data => setThreads(data))
                            .then(() => setTimeout(() => setLoading(false), 2000))
                            .catch(err => console.log(err))
                    )
                    .catch(err => console.log(err))
            )
            .catch(err => console.log(err))

    }, [])

    return (
        (loading) ? <Loading /> : 
        <div className="d-flex flex-row justify-content-around bg-primary rounded p-1" style={{width: "90%", maxWidth: "1200px", margin: "auto", boxShadow: "2px 6px 5px #0d2149c7"}}>
            <div className="d-flex flex-column bg-dark m-1 py-1 border border-light rounded justify-content-start" style={{ width: "30%", height: "650px" }}>
                <div className="d-flex flex-row justify-content-start text-light mb-3">
                    <p className="display-6 mb-0" style={(user.displayName.length > 12) ? { transform: "translateX(-45px) scale(0.45)" } : { transform: "translateX(-25px) scale(0.6)" }} >@ {user.displayName}</p>
                </div>
                <div className="border border-secondary rounded" style={{ width: "94%", height: "1px", margin: "0px auto" }} ></div>
                <div className={"text-light my-2 p-2 " + styles.conversations} >
                    <p className="text-secondary fw-bold ms-1" style={{ fontSize: "18pt" }} >conversations</p>
                    <div className="d-flex flex-column ms-2" >
                        {threads.map((user, idx) => {
                            return(
                                <div id={"thread-" + (idx +1)} key={idx} className="d-flex flex-row" style={{ cursor: "pointer" }} onClick={() => handleClick(user.id)}> <img src={lightConvo} alt="" style={{ transform: "scale(0.6)" }} /> <p className="ms-2" style={{ fontSize: "12pt" }} > {user.displayName} </p> </div>
                            )
                        })}
                    </div>
                </div>
                <div className="border border-secondary rounded" style={{ width: "94%", height: "1px", margin: "0px auto" }} ></div>
                <div className={"text-light my-2 p-2 " + styles.conversations} >
                    <div className="d-flex flex-row"><p className="text-secondary fw-bold ms-1" style={{ fontSize: "18pt" }} >all chatters</p> <img src={lightUsers} alt="" height="25px" className="ms-3 mt-1" /> </div>
                    <div className="d-flex flex-column ms-3" >
                        {allChatters.map((chatter, idx) => {
                            if (chatter.id !== user.id) return(
                                <div id={`chatter_${chatter.id}`} key={idx} className="d-flex flex-row" style={{ cursor: "pointer" }} onClick={() => handleClick(chatter.id)} > <p className="ms-2" style={{ fontSize: "12pt" }} > @ {chatter.displayName}</p> </div>
                            )
                        })}
                    </div>
                </div>
                <div className="border border-secondary rounded" style={{ width: "94%", height: "1px", margin: "0px auto" }} ></div>
            </div>
            <div className="d-flex flex-column justify-content-end rounded" style={{ width: "70%", height: "650px" }}>

                {(!chatting) ? 
                <div id="history" className="d-flex flex-column bg-dark border border-light text-light rounded m-1" style={{ height: "100%", width: "98%" }} ></div>
                :
                <History activeThread={activeThread} user={sessionStorage.getItem("id")} chatter={currentChatter} chatting={chatting} />
                }
                {(!chatting) ? 
                <form id="messageInput" onSubmit={handleSend} className="form" >
                    <div className="d-flex flex-row justify-content-between mt-1" >
                        <input type="text" id="blank" name="blankMessage" className="form-control bg-dark text-light ms-1" style={{ width: "100%" }} />
                        {(!chatting) ? <div className="ms-1 me-2" style={{ width: "66px" }} ></div> : <button type="submit" className="btn btn-outline-light ms-1 me-2" style={{ width: "60px" }} >send</button>}
                    </div>
                </form>
                : null}
            </div>
        </div>
    )
}

export default Chat
