import {Button} from 'antd';
import React, {Component} from 'react';
import "../../../styles/LoginPage/loginButton.css";
import {addError} from "./ShakeAnimation/addErrorClassToInput";

class LoginButton extends React.Component {
    constructor(props) {
        super(props);
    }

    state = {
        is_loading: false,
        username: '',
        password: '',
    }

    render() {
        return (
            <>
                <Button
                    type="primary"
                    htmlType="submit"
                    className="login_button"
                    onClick={addError}
                >
                    Log in
                </Button>
            </>
        );
    }
}

export default LoginButton;