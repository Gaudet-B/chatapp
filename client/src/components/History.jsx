import React from 'react'


const History = props => {

    const { activeThread } = props

    return (
        <div id="history" className="d-flex flex-column bg-dark border border-light text-light rounded m-1" style={{ height: "100%", width: "98%" }} >
            {(activeThread.history) ? activeThread.history.map((message, idx) => {
                return(
                    <div key={idx}>{message.content}</div>
                )
            }) : null}
        </div>
    )
}

export default History
