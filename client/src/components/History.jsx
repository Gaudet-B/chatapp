import { useEffect, useState } from 'react'
import SockJsClient from 'react-stomp'

import styles from './chat.style.module.css'


const History = props => {

    const [ history, setHistory ] = useState([])
    const [ otherUser, setOtherUser ] = useState()
    const [ connecting, setConnecting ] = useState(true)
    const [ messages, setMessages ] = useState([])
    const [ messageText, setMessageText ] = useState()
    const [ connected, setConnected ] = useState(false)
    const [ clientRef, setClientRef ] = useState()

    const { activeThread, chatting, user, chatter } = props

    const headers = {
        fromUser: user,
        toUser: chatter,
        crossDomain: true,
        withCredentials: true,
    }

    const handleChange = e => setMessageText(e.target.value)

    const handleSend = e => {
        e.preventDefault()

        // let content = document.getElementById("msg").value;
        let content = messageText
        let json = JSON.stringify({
            "from": user,
            "to": chatter,
            "content": content
        });
        clientRef.sendMessage("/app/all", json)
        document.getElementById("chatInput").value = ""
    }

    const onMessageReceive = (msg, topic) => setMessages(messages => [...messages, msg])

    useEffect(() => {
        fetch(`http://localhost:8080/api/history/${activeThread.id}`)
            .then(res => res.json())
            .then(data => setHistory(data))
            .then(
                fetch(`http://localhost:8080/api/getuser/${chatter}`)
                    .then(res => res.json())
                    .then(data => {
                        setOtherUser(data)
                        setConnecting(!connecting)
                    })
                    .catch(err => console.log(err))
            )
            .catch(err => console.log(err))

    }, [])

    return (
        <div style={{ height: "100%", width: "100%" }}>
            <div id="history" className="d-flex flex-column justify-content-between bg-dark border border-light text-light rounded m-1 p-2" style={{ height: "93.5%", width: "98%" }} >
                {(connecting) ? 
                <p className="fs-5 text-secondary">connecting...</p>
                :
                <p className="fs-5 text-secondary">@{otherUser.displayName}</p>
                }
                <div className={styles.history}>

                {(activeThread.history) ? activeThread.history.map((message, idx) => {
                    return(
                        (message.from === sessionStorage.getItem("id")) ?
                        <div className="text-end" key={idx}>{message.content}</div>
                        :
                        <div className="text-start" key={idx}>{message.content}</div>
                    )
                }) : null}

                {(messages.length > 0) ? messages.map((message, idx) => {
                    return(
                        (message.from === sessionStorage.getItem("id")) ?
                        <div className="text-end" key={idx}>{message.content}</div>
                        :
                        <div className="text-start" key={idx}>{message.content}</div>
                    )
                }) : null}
                <SockJsClient url={ "http://localhost:8080/handler" } headers={headers} topics={["/topic/all"]}
                        onMessage={onMessageReceive} ref={(client) => setClientRef(client)}
                        onConnect={() => setConnected(true)}
                        onDisconnect={() => setConnected(false)}
                        debug={ false }/>
                </div>
            </div>
            <form id="messageInput" onSubmit={handleSend} className="form" >
                <div className="d-flex flex-row justify-content-between mt-1" >
                    <input onChange={handleChange} type="text" id="chatInput" name="message" className="form-control bg-dark text-light ms-1" style={{ width: "100%" }} />
                    {(!chatting) ? <div className="ms-1 me-2" style={{ width: "66px" }} ></div> : <button type="submit" className="btn btn-outline-light ms-1 me-2" style={{ width: "60px" }} >send</button>}
                </div>
            </form>
        </div>
    )
}

export default History
