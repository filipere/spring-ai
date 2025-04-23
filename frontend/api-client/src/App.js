import { useState } from 'react';

import { tab } from '@testing-library/user-event/dist/tab';
import TalkWithAI from './pages/chat/TalkWithAI';
import RecipeGenerator from './pages/recipe/RecipeGenerator';
import ImageGenerator from './pages/image/ImageGenerator';

import './App.css';

function App() {

  const [activeTab, setActiveTab] = useState('ask-ai')

  const handleTabChange = (tab) => {
    //alert(tab);
    setActiveTab(tab);
  }

  return (
    <div className="App">
      <button
        className={activeTab === 'ask-ai' ? 'active': ''}
        onClick={() => handleTabChange('ask-ai')}>
          Pergunte a IA
      </button>
      <button 
        className={activeTab === 'recipe-generator' ? 'active': ''}
        onClick={() => handleTabChange('recipe-generator')}>
          Gerador de Receitas
      </button>
      <button
        className={activeTab === 'image-generator' ? 'active': ''}
        onClick={() => handleTabChange('image-generator')}>
          Gerador de Imagens
      </button>
      <div>
        {activeTab === 'ask-ai' && <TalkWithAI/>}
        {activeTab === 'recipe-generator' && <RecipeGenerator/>}
        {activeTab === 'image-generator' && <ImageGenerator/>}
      </div>
    </div>
  );
}

export default App;
