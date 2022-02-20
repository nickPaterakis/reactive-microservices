/* eslint-disable jsx-a11y/click-events-have-key-events */
import React, { useState, useEffect, useRef } from 'react';
import PropTypes from 'prop-types';
import useOutsideClick from '../../hooks/useOutsideClick';
import Aux from '../../hoc/Auxiliary';
import { getCountries } from '../../api/CountriesService';

const Autocomplete = ({ handleLocation, countries }) => {
  const [state, setState] = useState({
    activeOption: 0,
    filteredOptions: [''],
    showOptions: false,
  });
  const wrapperRef = useRef(null);
  useOutsideClick(wrapperRef, () => setState({ ...state, showOptions: false }));
  console.log(state);

  // useEffect(() => {
  //   if (state.showOptions && state.userInput) {
  //     const fetchData = async () => {
  //       const result = await getCountries(state.userInput);
    
  //       setState({
  //         ...state,
  //         filteredOptions: result.data.map((country) => country.name),
  //       });
  //     };
    
  //     fetchData();
  //   }
  // }, [state.userInput]);

  const onChange = (e) => {
    console.log('onChange');
    handleLocation(e.target.value);
    const userInput = e.currentTarget.value;
 
    const filteredOptions = countries.filter(
      (country) => country.substr(0, userInput.length).toLowerCase() === userInput.toLowerCase(),
    );
    console.log(filteredOptions);
    setState({
      ...state,
      filteredOptions,
      showOptions: true,
      userInput: e.target.value,
    });
  };

  const onClick = (e) => {
    handleLocation(e.currentTarget.innerText);
    console.log('onClick');
    setState({
      activeOption: 0,
      filteredOptions: [],
      showOptions: false,
      userInput: e.currentTarget.innerText,
    });
  };

  const onKeyDown = (e) => {
    const { activeOption, filteredOptions } = state;
    console.log('onkeydown');
    if (e.keyCode === 13) { // Enter
      handleLocation(filteredOptions[activeOption]);
      setState({
        activeOption: 0,
        showOptions: false,
        userInput: filteredOptions[activeOption],
      });
    } else if (e.keyCode === 38) { // ArrowUp
      if (activeOption === 0) {
        return;
      }
      setState({ ...state, activeOption: activeOption - 1 });
    } else if (e.keyCode === 40) { // ArrowDown
      if (activeOption === filteredOptions?.length - 1) {
        return;
      }
      setState({ ...state, activeOption: activeOption + 1 });
    }
  };

  console.log(state.userInput);

  const {
    activeOption, filteredOptions, showOptions, userInput,
  } = state;

  let optionList;
  if (showOptions && userInput) {
    if (filteredOptions?.length) {
      optionList = (
        <ul className="options" ref={wrapperRef}>
          {filteredOptions.map((optionName, index) => {
            let className;
            if (index === activeOption) {
              className = 'option-active';
            }
            return (
              // eslint-disable-next-line jsx-a11y/no-noninteractive-element-interactions
              <li className={className} key={optionName} onClick={onClick}>
                {optionName}
              </li>
            );
          })}
        </ul>
      );
    }
  }

  return (
    <Aux>
      <div className="search">
        <input
          type="text"
          className="input input--search"
          placeholder="Where are you going?"
          onChange={onChange}
          onKeyDown={onKeyDown}
          value={userInput}
         // onKeyPress={onKeyUp}
        />
      </div>
      {optionList}
    </Aux>
  );
};

Autocomplete.propTypes = {
  handleLocation: PropTypes.func.isRequired,
};

export default Autocomplete;
