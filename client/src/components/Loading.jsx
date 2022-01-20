import React from 'react'
import styles from './chat.style.module.css'


const Loading = () => {
    return (
        <div>
            <div className={styles.spinner}></div>
            <div className={styles.mediumSpinner}></div>
            <div className={styles.smallSpinner}></div>
        </div>
    )
}

export default Loading
