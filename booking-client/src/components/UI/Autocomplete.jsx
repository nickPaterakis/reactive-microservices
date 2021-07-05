/* eslint-disable jsx-a11y/click-events-have-key-events */
import React, { useState, useEffect, useRef } from 'react';
import PropTypes from 'prop-types';
import useOutsideClick from '../../hooks/useOutsideClick';
import Aux from '../../hoc/Auxiliary';
import { getCountries } from '../../api/CountriesService';

const Autocomplete = ({ handleLocation }) => {
  const [state, setState] = useState({
    activeOption: 0,
    filteredOptions: [''],
    showOptions: false,
    userInput: '',
  });
  const wrapperRef = useRef(null);
  useOutsideClick(wrapperRef, () => setState({ ...state, showOptions: false }));

  useEffect(() => {
    if (state.showOptions && state.userInput) {
      const fetchData = async () => {
        const result = await getCountries(state.userInput);
    
        setState({
          ...state,
          filteredOptions: result.data.map((country) => country.name),
        });
      };
    
      fetchData();
    }
  }, [state.userInput]);

  const onChange = (e) => {
    handleLocation(e.currentTarget.value);

    setState({
      ...state,
      showOptions: true,
      userInput: e.currentTarget.value,
    });
  };

  const onClick = (e) => {
    handleLocation(e.currentTarget.innerText);
    setState({
      activeOption: 0,
      filteredOptions: [],
      showOptions: false,
      userInput: e.currentTarget.innerText,
    });
  };

  const onKeyDown = (e) => {
    const { activeOption, filteredOptions } = state;

    if (e.keyCode === 13) {
      handleLocation(filteredOptions[activeOption]);
      setState({
        activeOption: 0,
        showOptions: false,
        userInput: filteredOptions[activeOption],
      });
    } else if (e.keyCode === 38) {
      if (activeOption === 0) {
        return;
      }
      setState({ ...state, activeOption: activeOption - 1 });
    } else if (e.keyCode === 40) {
      if (activeOption === filteredOptions?.length - 1) {
        return;
      }
      setState({ ...state, activeOption: activeOption + 1 });
    }
  };

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
