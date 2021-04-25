import React, { useState } from 'react';

const SignInForm = () => {
  const [signInForm, setSignInForm] = useState({
    formControls: {
      email: {
        value: '',
        validation: {
          valid: true,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
        },
      },
      password: {
        value: '',
        validation: {
          valid: true,
          errorMessage: '',
        },
        validationRules: {
          isRequired: true,
        },
      },
    }, 
  });
  
  const changeHandler = (event) => {
    const { name } = event.target;
    const { value } = event.target;

    const updatedControls = {
      ...signInForm.formControls,
    };
  
    updatedControls[name].value = value;
       
    setSignInForm({
      formControls: updatedControls,
    });
  };

  const formSubmitHandler = (event) => {
    event.preventDefault();
  };

  return (
    <div>
      <form className="form" onSubmit={formSubmitHandler}>
        <label className="label label--white" htmlFor="email">
          Email
          <input
            className="input input--sign-in"
            type="email"
            name="email"
            value={signInForm.formControls.email.value}
            onChange={changeHandler}
            valid={signInForm.formControls.email.validation.valid}
          />
        </label>

        <label className="label label--white" htmlFor="password">
          Password     
          <input
            className="input input--sign-in"
            type="password"
            name="password"
            value={signInForm.formControls.password.value}
            onChange={changeHandler}
            valid={signInForm.formControls.password.validation.valid}
          />
        </label>

        <button type="submit" className="btn btn--sign-in">Sign in</button>
      </form>
      
    </div>
  );
};

export default SignInForm;
